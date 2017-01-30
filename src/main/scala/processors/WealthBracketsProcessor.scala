package processors

import models._
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper

import scala.util.matching.Regex

object WealthBracketsProcessor {
  def process(country: Country, pdDocument: PDDocument) : Vector[WealthBracket] = {

    val matchedCountries = getLines(pdDocument)
      .filter(line => countryMatch(line, country.name))

    if (matchedCountries.nonEmpty)
      matchedCountries.map(getDetail).head
    else
      Vector()
  }

  def getLines(pdDocument: PDDocument) : Vector[String] = {
    val stripper = new PDFTextStripper()

    stripper.setStartPage(111)
    stripper.setEndPage(114)

    val text = stripper.getText(pdDocument)

    text
      .split('\n')
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

  def getDetail(line: String) : Vector[WealthBracket] = {

    val regexString = """^.+
                        |([\d.]+)\s+
                        |([\d.]+)\s+
                        |([\d.]+)\s+
                        |([\d.]+)\s+
                        |([\d.]+)\s+
                        |([\d.]+)\s+
                        |([\d.]+)\s+
                        |([\d.]+)\s+
                        |([\d.]+)\s+
                        |([\d.]+)\s+
                        |([\d.]+)\s+
                        |([\d.]+)""".stripMargin.replaceAll("\n", "").trim

    val regex = new Regex(regexString,
      "decile10",
      "decile20",
      "decile30",
      "decile40",
      "decile50",
      "decile60",
      "decile70",
      "decile80",
      "decile90",
      "top10",
      "top5",
      "top1"

    )

    val result = regex.findFirstMatchIn(line).get

    Vector(
      WealthBracket(bracketType = WealthBracketType.Decile, bracket = .1, value = result.group("decile10").toDouble),
      WealthBracket(bracketType = WealthBracketType.Decile, bracket = .2, value = result.group("decile20").toDouble),
      WealthBracket(bracketType = WealthBracketType.Decile, bracket = .3, value = result.group("decile30").toDouble),
      WealthBracket(bracketType = WealthBracketType.Decile, bracket = .4, value = result.group("decile40").toDouble),
      WealthBracket(bracketType = WealthBracketType.Decile, bracket = .5, value = result.group("decile50").toDouble),
      WealthBracket(bracketType = WealthBracketType.Decile, bracket = .6, value = result.group("decile60").toDouble),
      WealthBracket(bracketType = WealthBracketType.Decile, bracket = .7, value = result.group("decile70").toDouble),
      WealthBracket(bracketType = WealthBracketType.Decile, bracket = .8, value = result.group("decile80").toDouble),
      WealthBracket(bracketType = WealthBracketType.Decile, bracket = .9, value = result.group("decile90").toDouble),
      WealthBracket(bracketType = WealthBracketType.Top, bracket = .1, value = result.group("top10").toDouble),
      WealthBracket(bracketType = WealthBracketType.Top, bracket = .05, value = result.group("top5").toDouble),
      WealthBracket(bracketType = WealthBracketType.Top, bracket = .01, value = result.group("top1").toDouble)
    )
  }
}
