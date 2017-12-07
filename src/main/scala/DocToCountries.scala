import models.country.Country
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper

import scala.util.matching.Regex

object DocToCountries {
  def process(pdDocument: PDDocument) : Vector[Country] = {

    getLines(pdDocument)
      .filter(lineContainsDataMatch)
      .map(lineToCountry)
  }

  private def getLines(pdDocument: PDDocument) : Vector[String] = {
    val stripper = new PDFTextStripper()

    stripper.setStartPage(21)
    stripper.setEndPage(24)

    val text = stripper.getText(pdDocument)

    text
      .split('\n')
      .map(_.trim)
      .toVector
  }

  private def lineContainsDataMatch(line: String) : Boolean = {
    line.matches(".*(Low income|Lower middle income|Upper middle income|High income).*")
  }

  private def lineToCountry(line: String) : Country = {
    val regexString = """^(.+)
                |\s(Africa|Asia-Pacific|Europe|North America|Latin America|China|India)""".stripMargin.replaceAll("\n", "").trim

    val regex = new Regex(regexString,
      "country",
      "region"
    )

    val result = regex.findFirstMatchIn(line).get

    Country(
      name = result.group("country"),
      region = result.group("region")
    )
  }
}
