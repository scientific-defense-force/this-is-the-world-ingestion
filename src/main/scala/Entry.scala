import aggregator._
import models.AggregateDetails
import models.country.{Country, CountryData}
import models.region.RegionData
import org.apache.pdfbox.pdmodel.PDDocument
import processors.WealthBracketsProcessor
import processors.country.{IsWenaoProcessor, PopulationProcessor, WealthDetailsProcessor}
import io.circe.generic.auto._
import io.circe.syntax._

object Entry {


  def main(args: Array[String]): Unit = {

    val fileAsStream = RetrieveFileAsStream.get()
    val doc = LoadIntoPdfBox.load(fileAsStream)

    val countriesResult = DocToCountries.process(doc)
          .map(c => createCountryData(c, doc))

    //the region data isn't actually being used.. but could add more data and use it in the future?
    val regionsResult = Regions.get
          .map(r => createRegionData(r, doc))

    val aggregateDetails = createAggregteDetail(countriesResult, regionsResult)

    val aggregateDetailsString = aggregateDetails.asJson.spaces2

    WriteStringToFile.write(aggregateDetailsString, "aggregateDetails2018.json")

    println(s"Completed Processing ${countriesResult.size} entries")

    doc.close()
  }

  def createCountryData(country: Country, doc: PDDocument): CountryData = {
    CountryData(
      name = country.name,
      region = country.region,
      isWenao = IsWenaoProcessor.isIt(country.name),
      population = PopulationProcessor.process(country, doc),
      wealthDetails = WealthDetailsProcessor.process(country, doc),
      wealthBracketDetails = WealthBracketsProcessor.process(country.name, doc)
    )
  }

  def createRegionData(region: String, doc: PDDocument): RegionData = {
    RegionData(
      region = region,
      wealthBracketDetails = WealthBracketsProcessor.process(region, doc)
    )
  }

  def createAggregteDetail(countryData: Vector[CountryData], regionData: Vector[RegionData]): AggregateDetails = {
    AggregateDetails(
      otherTotalWealth = OtherTotalWealthAggregator.process(countryData),
      wenaoTotalWealth = WenaoTotalWealthAggregator.process(countryData),
      otherTotalPopulation = OtherTotalPopulationAggregator.process(countryData),
      wenaoTotalPopulation = WenaoTotalPopulationAggregator.process(countryData),
      wenao1PercentWealth = Wenao1PercentWealthAggregator.process(countryData),
      wenao99PercentWealth = Wenao99PercentWealthAggregator.process(countryData),
      other10PercentWealth = Other10PercentWealthAggregator.process(countryData),
      other90PercentWealth = Other90PercentWealthAggregator.process(countryData)
    )
  }
}
