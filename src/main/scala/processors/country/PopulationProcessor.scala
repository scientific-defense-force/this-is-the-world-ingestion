package processors.country

import models.country.Country
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper

import scala.util.matching.Regex

object PopulationProcessor {
  def process(country: Country, pdDocument: PDDocument): Long = {
    getLines(pdDocument)
      .filter(line => countryMatch(line, country.name))
      .map(getDetail)
      .head
  }

  private var linesResult: Option[Vector[String]] = None

  private def getLines(pdDocument: PDDocument) : Vector[String] = {
    if (linesResult.nonEmpty) {
      return linesResult.get
    }

    val stripper = new PDFTextStripper()

    stripper.setStartPage(23)
    stripper.setEndPage(26)

    val text = stripper.getText(pdDocument)

    linesResult = Some(text
                    .split("\\r?\\n")
                    .map(_.trim)
                    .filterNot(_.isEmpty)
                    .toVector)

    linesResult.get
  }

  private def countryMatch(line: String, name: String): Boolean = {
    val escapedCountry = name
      .replaceAll("""\(""", """\\(""")
      .replaceAll("""\)""", """\\)""")
      .replaceAll("""\.""", """\\.""")

    line.matches(s"^$escapedCountry\\s+\\d+.*")
  }

  private def getDetail(line: String): Long = {
    val regexString = """([\d,]+)\s*$""".stripMargin.trim

    val regex = new Regex(regexString,
      "2018-population"
    )

    val result = regex.findFirstMatchIn(line).get

    result.group("2018-population").filter((char) => char != ',').toLong * 1000
  }
}
