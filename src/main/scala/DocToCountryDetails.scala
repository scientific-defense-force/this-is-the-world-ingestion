import models.CountryDetail
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper

import scala.util.matching.Regex

object DocToCountryDetails {
  def process(pdDocument: PDDocument) : Vector[CountryDetail] = {

    getLines(pdDocument)
      .filter(lineContainsDataMatch)
      .map(lineToCountryDetail)
  }

  def getLines(pdDocument: PDDocument) : Vector[String] = {
    val stripper = new PDFTextStripper()

    stripper.setStartPage(18)
    stripper.setEndPage(21)

    val text = stripper.getText(pdDocument)

    text
      .split('\n')
      .map(_.trim)
      .toVector
  }

  def lineContainsDataMatch(line: String) : Boolean = {
    line.matches(".*(Low income|Lower middle income|Upper middle income|High income).*")
  }

  def lineToCountryDetail(line: String) : CountryDetail = {
    val regexString = """^(.+)
                |\s(Africa|Asia-Pacific|Europe|North America|Latin America|China|India)
                |\s(Low income|Lower middle income|Upper middle income|High income)
                |\s+([0-9,]+)?
                |\s+([0-9,]+)
                |\s+([0-9,]+)
                |\s+([0-9,-]+)
                |\s+([0-9.]+)\s""".stripMargin.replaceAll("\n", "").trim

    val regex = new Regex(regexString,
      "country",
      "region",
      "incomeGroup",
      "gdpPerAdult2016",
      "wealthPerAdult2000",
      "wealthPerAdult2016",
      "totalWeatlh",
      "shareOfWorldWealth2016"
    )

    val result = regex.findFirstMatchIn(line).get

    var gdpPerAdult2016 : Option[Int] = None

    if (result.group("gdpPerAdult2016") != null) {
      gdpPerAdult2016 = Some(result.group("gdpPerAdult2016").filter((char) => char != ',').toInt)
    }

    var totalWeatlh : Long = 0

    if (result.group("totalWeatlh") != null && result.group("totalWeatlh") != "-") {
      totalWeatlh = result.group("totalWeatlh").filter((char) => char != ',').toLong * 1000000000
    }

    CountryDetail(
      name = result.group("country"),
      region = result.group("region"),
      incomeGroup = result.group("incomeGroup"),
      gdpPerAdult2016 = gdpPerAdult2016,
      wealthPerAdult2000 = result.group("wealthPerAdult2000").filter((char) => char != ',').toInt,
      weatlhPerAdult2016 = result.group("wealthPerAdult2016").filter((char) => char != ',').toInt,
      totalWeatlh = totalWeatlh,
      shareOfWorldWealth2016 = result.group("shareOfWorldWealth2016").filter((char) => char != ',').toDouble
    )
  }
}
