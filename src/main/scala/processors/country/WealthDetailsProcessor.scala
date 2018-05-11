package processors.country

import models.country.{Country, WealthDetails}
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper

import scala.util.matching.Regex

object WealthDetailsProcessor {
  def process(country: Country, pdDocument: PDDocument): WealthDetails = {

    getLines(pdDocument)
      .filter(line => countryMatch(line, country.name))
      .map(getDetail)
      .head
  }

  private var linesResult : Option[Vector[String]] = None

  def getLines(pdDocument: PDDocument): Vector[String] = {
    if (linesResult.nonEmpty) {
      return linesResult.get
    }

    val stripper = new PDFTextStripper()

    stripper.setStartPage(21)
    stripper.setEndPage(24)

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

    line.matches(s"^$escapedCountry\\s+(?:Africa|Asia-Pacific|Europe|North America|Latin America|China|India).*")
  }

  def getDetail(line: String): WealthDetails = {
    val regexString = """^.+
                |\s(?:Africa|Asia-Pacific|Europe|North America|Latin America|China|India)
                |\s(Low income|Lower middle income|Upper middle income|High income)
                |\s+([0-9,]+)?
                |\s+([0-9,]+)
                |\s+([0-9,]+)
                |\s+([0-9,-]+)
                |\s+([0-9.]+)\s""".stripMargin.replaceAll("\n", "").trim

    val regex = new Regex(regexString,
      "incomeGroup",
      "gdpPerAdult2017",
      "wealthPerAdult2000",
      "wealthPerAdult2017",
      "totalWeatlh",
      "shareOfWorldWealth2017"
    )

    val result = regex.findFirstMatchIn(line).get

    var gdpPerAdult2017 : Option[Int] = None

    if (result.group("gdpPerAdult2017") != null) {
      gdpPerAdult2017 = Some(result.group("gdpPerAdult2017").filter((char) => char != ',').toInt)
    }

    var totalWeatlh : Long = 0

    if (result.group("totalWeatlh") != null && result.group("totalWeatlh") != "-") {
      totalWeatlh = result.group("totalWeatlh").filter((char) => char != ',').toLong * 1000000000
    }

    WealthDetails(
      incomeGroup = result.group("incomeGroup"),
      gdpPerAdult2017 = gdpPerAdult2017,
      wealthPerAdult2000 = result.group("wealthPerAdult2000").filter((char) => char != ',').toInt,
      weatlhPerAdult2017 = result.group("wealthPerAdult2017").filter((char) => char != ',').toInt,
      totalWeatlh = totalWeatlh,
      shareOfWorldWealth2017 = result.group("shareOfWorldWealth2017").filter((char) => char != ',').toDouble
    )
  }
}
