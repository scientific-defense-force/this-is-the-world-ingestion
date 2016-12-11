import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper

import scala.util.matching.Regex

/**
  * Created by alistairburrowes on 2016/11/15.
  */
object DocToCountryDetails {
  def process(pdDocument: PDDocument) : Vector[CountryDetail] = {
    val stripper = new PDFTextStripper()

    stripper.setStartPage(18)
    stripper.setEndPage(21)

    val text = stripper.getText(pdDocument)

    val dataLines = getTableLines(text)

//    val countryDetails = dataLines.map(lineToCountryDetail)

    val countryDetail = lineToCountryDetail(dataLines.head)

    print(countryDetail)

//    new Vector[CountryDetail](new CountryDetail())
    null
  }

  def getTableLines(text: String) : Vector[String] = {
    text
      .split('\n')
      .filter((line) => {
        line.matches(".*(Low income|Lower middle income|Upper middle income|High income).*")
      })
      .toVector
  }

  //Afghanistan Asia-Pacific Low income  1,604   848   2,500   38  0.0 n.a.

  def lineToCountryDetail(line: String) : CountryDetail = {
    val regex = new Regex(
      """
        | $([\w\s.-])
        | \w(Africa|Asia-Pacific|Europe|North America|Latin America)
        | \w(Low income|Lower middle income|Upper middle income|High income)
        | \w+([0-9,]+)
        | \w+([0-9,]+)
        | \w+([0-9,]+)
        | \w+([0-9,]+)
        | \w+([0-9.]+)\w
      """, "country", "region", "incomeGroup", "gdpPerAdult2016", "wealthPerAdult2000", "weatlhPerAdult2016", "totalWeatlh", "shareOfWorldWealth2016")

    val result = regex.findAllIn(line)

    new CountryDetail(
      name = result.group("country"),
      region = result.group("region"),
      incomeGroup = result.group("incomeGroup"),
      gdpPerAdult2016 = result.group("gdpPerAdult2016").filter((char) => char != ',').toInt,
      wealthPerAdult2000 = result.group("wealthPerAdult2000").filter((char) => char != ',').toInt,
      weatlhPerAdult2016 = result.group("weatlhPerAdult2016").filter((char) => char != ',').toInt,
      totalWeatlh = result.group("totalWeatlh").filter((char) => char != ',').toInt,
      shareOfWorldWealth2016 = result.group("shareOfWorldWealth2016").filter((char) => char != ',').toDouble
    )
  }
}
