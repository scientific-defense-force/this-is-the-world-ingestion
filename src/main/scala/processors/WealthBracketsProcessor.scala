package processors

import models._
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper

import scala.util.matching.Regex

object WealthBracketsProcessor {
  def process(identifier: String, pdDocument: PDDocument): Vector[WealthBracket] = {

    val matchedCountries = getLines(pdDocument)
      .filter(line => identifierMatch(line, identifier))

    if (matchedCountries.nonEmpty)
      matchedCountries.map(getDetail).head
    else
      //in some cases there is no wealth bracket data for a given country, not ideal but these are all
      // non-wenao countries with very low wealth. Could look to use region totals to work around this?
      Vector()
  }

  private var linesResult: Option[Vector[String]] = None

  def getLines(pdDocument: PDDocument): Vector[String] = {
    if (linesResult.nonEmpty) {
      return linesResult.get
    }

    val stripper = new PDFTextStripper()

    stripper.setStartPage(118)
    stripper.setEndPage(121)

    val text = stripper.getText(pdDocument)

    linesResult = Some(text
      .split("\\r?\\n")
      .map(_.trim)
      .filterNot(_.isEmpty)
      .toVector)

    linesResult.get
  }

  private def identifierMatch(line: String, name: String): Boolean = {
    val escapedIdentifier = name
      .replaceAll("""\(""", """\\(""")
      .replaceAll("""\)""", """\\)""")
      .replaceAll("""\.""", """\\.""")

    line.matches(s"^$escapedIdentifier\\s+\\d+.*")
  }

  def getDetail(line: String): Vector[WealthBracket] = {

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
      "decile100",
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
      WealthBracket(bracketType = WealthBracketType.Decile, bracket = 1, value = result.group("decile100").toDouble),
      WealthBracket(bracketType = WealthBracketType.Top, bracket = .05, value = result.group("top5").toDouble),
      WealthBracket(bracketType = WealthBracketType.Top, bracket = .01, value = result.group("top1").toDouble)
    )
  }
}
