package processors

import models.Country
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper

import scala.util.matching.Regex

object PopulationProcessor {
  def process(country: Country, pdDocument: PDDocument) : Long = {
    getLines(pdDocument)
      .filter(line => countryMatch(line, country.name))
      .map(getDetail)
      .head
  }

  private def getLines(pdDocument: PDDocument) : Vector[String] = {
    val stripper = new PDFTextStripper()

    stripper.setStartPage(22)
    stripper.setEndPage(25)

    val text = stripper.getText(pdDocument)

    text
      .split("\\r?\\n")
      .map(_.trim)
      .filterNot(_.isEmpty)
      .toVector
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
      "2016-population"
    )

    val result = regex.findFirstMatchIn(line).get

    result.group("2016-population").filter((char) => char != ',').toLong * 1000
  }
}
