import models.CountryPopulationDetail
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper

import scala.util.matching.Regex

object DocToCountryPopulationDetails {
  def process(pdDocument: PDDocument) : Vector[CountryPopulationDetail] = {
    val stripper = new PDFTextStripper()

    stripper.setStartPage(22)
    stripper.setEndPage(25)

    val text = stripper.getText(pdDocument)

    val dataLines = getTableLines(text)

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

    CountryPopulationDetail(
      name = result.group("country").trim,
      population = result.group("2016-population").filter((char) => char != ',').toLong * 1000
    )
  }
}
