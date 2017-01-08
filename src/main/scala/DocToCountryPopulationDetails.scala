import models.CountryPopulationDetail
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper

import scala.util.matching.Regex

object DocToCountryPopulationDetails {
  def process(pdDocument: PDDocument) : Vector[CountryPopulationDetail] = {
    val stripper = new PDFTextStripper()

    stripper.setStartPage(22)
    stripper.setEndPage(29)

    val text = stripper.getText(pdDocument)

    val dataLines = getTableLines(text)

    dataLines.map(lineToCountryDetail)
  }

  def getTableLines(text: String) : Vector[String] = {
    text
      .split('\n')
      .filter((line) => {
        line.matches("^\w+([\w\s]+)?[\d,]+\s[\d,]+\s[\d,]+\s[\d,]+\s[\d,]+\s[\d,]+\s[\d,]+\s[\d,]+\s[\d,]+")
      })
      .toVector
  }

  def lineToCountryDetail(line: String) : CountryPopulationDetail = {
    val regexString = "^(.+)\s\d.+([\d,])$"

    val regex = new Regex(regexString,
      "country",
      "2016-population"
    )

    val result = regex.findFirstMatchIn(line).get

    CountryPopulationDetail(
      name = result.group("country"),
      population = result.group("2016-population").filter((char) => char != ',').toInt
    )
  }

  val oecdCountries = List("Australia", "Austria", "Belgium", "Canada", "Chile", "Czech Republic", "Denmark", "Estonia", "Finland", "France", "Germany", "Greece", "Hungary", "Iceland", "Ireland", "Israel", "Italy", "Japan", "Korea", "Luxembourg", "Mexico", "Netherlands", "New Zealand", "Norway", "Poland", "Portugal", "Slovak Republic", "Slovenia", "Spain", "Sweden", "Switzerland", "Turkey", "United Kingdom", "United States")

  def isOecdCountry(countryName: String) : Boolean = {
    oecdCountries.contains(countryName)

  }
}
