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

//    print(text)

//    print(dataLines.length)

    dataLines.map(lineToCountryDetail)
  }

  def getTableLines(text: String) : Vector[String] = {
    text
      .split('\n')
      .filter((line) => {
        line.matches("""^\w(.+)?[\d,]+\s+[\d,]+\s+[\d,]+\s+[\d,]+\s+[\d,]+\s+[\d,]+\s+[\d,]+\s+[\d,]+\s+[\d,]+\s*""".stripMargin.replaceAll("\n", "").trim)
      })
      .toVector
  }

  def lineToCountryDetail(line: String) : CountryPopulationDetail = {
    val regexString = """^(.+)\s+[\d,]+\s+[\d,]+\s+[\d,]+\s+[\d,]+\s+[\d,]+\s+[\d,]+\s+[\d,]+\s+[\d,]+\s+([\d,]+)\s*""".stripMargin.replaceAll("\n", "").trim

    val regex = new Regex(regexString,
      "country",
      "2016-population"
    )

    val result = regex.findFirstMatchIn(line).get

//    println(result.group("country"))
//    println(result.group("2016-population"))

    CountryPopulationDetail(
      name = result.group("country"),
      population = result.group("2016-population").filter((char) => char != ',').toInt
    )
  }
}
