package processors.country

import models.country.Country
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper

import scala.util.matching.Regex

object PopulationProcessor {
  def process(country: Country, pdDocument: PDDocument) : Long = {
    getLines(pdDocument)
      .filter(line => countryMatch(line, countryName(country)))
      .map(getDetail)
      .head
  }

  private var linesResult : Option[Vector[String]] = None

  private def getLines(pdDocument: PDDocument) : Vector[String] = {
    if (linesResult.nonEmpty) {
      return linesResult.get
    }

    val stripper = new PDFTextStripper()

    stripper.setStartPage(25)
    stripper.setEndPage(28)

    val text = stripper.getText(pdDocument)

    linesResult = Some(text
                    .split("\\r?\\n")
                    .map(_.trim)
                    .filterNot(_.isEmpty)
                    .toVector)

    linesResult.get
  }

  private def countryName(country: Country): String = {
    if (country.name == "St. Vincent and the Grenadines")
      "Grenadines" //this is split of 2 lines with the second line with the data
    else
      country.name
  }

  private def countryMatch(line: String, name: String) : Boolean = {
    val escapedCountry = name
      .replaceAll("""\(""", """\\(""")
      .replaceAll("""\)""", """\\)""")
      .replaceAll("""\.""", """\\.""")

    line.matches(s"^$escapedCountry\\s+\\d+.*")
  }

  private def getDetail(line: String) : Long = {
    val regexString = """([\d,]+)\s*$""".stripMargin.trim

    val regex = new Regex(regexString,
      "2017-population"
    )

    val result = regex.findFirstMatchIn(line).get

    result.group("2017-population").filter((char) => char != ',').toLong * 1000
  }
}
