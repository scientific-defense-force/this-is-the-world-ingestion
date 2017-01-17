import models.CountryPopulationDetail
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper

import scala.util.matching.Regex

object DocToCountryPopulationDetails {
  def process(pdDocument: PDDocument) : Vector[CountryPopulationDetail] = {
    getLines(pdDocument)
      .filter(lineContainsDataMatch)
      .map(lineToCountryDetail)
  }

  def getLines(pdDocument: PDDocument) : Vector[String] = {
    val stripper = new PDFTextStripper()

    stripper.setStartPage(22)
    stripper.setEndPage(25)

    val text = stripper.getText(pdDocument)

    text.split('\n').toVector
  }

  def lineContainsDataMatch(line: String) : Boolean = {
    line.matches("""^\w(.+)?[\d,]+\s+[\d,]+\s+[\d,]+\s+[\d,]+\s+[\d,]+\s+[\d,]+\s+[\d,]+\s+[\d,]+\s+[\d,]+\s*""".stripMargin.replaceAll("\n", "").trim)
  }

  def lineToCountryDetail(line: String) : CountryPopulationDetail = {
    val regexString = """^(.+)\s+[\d,]+\s+[\d,]+\s+[\d,]+\s+[\d,]+\s+[\d,]+\s+[\d,]+\s+[\d,]+\s+[\d,]+\s+([\d,]+)\s*""".stripMargin.replaceAll("\n", "").trim

    val regex = new Regex(regexString,
      "country",
      "2016-population"
    )

    val result = regex.findFirstMatchIn(line).get

    CountryPopulationDetail(
      name = result.group("country").trim,
      population = result.group("2016-population").filter((char) => char != ',').toLong * 1000
    )
  }
}
