import models.country.{Country, CountryData}
import models.region.RegionData
import org.apache.pdfbox.pdmodel.PDDocument
import processors.WealthBracketsProcessor
import processors.country.{IsWenaoProcessor, PopulationProcessor, WealthDetailsProcessor}

object Entry {


  def main(args: Array[String]) {

    val fileAsStream = RetrieveFileAsStream.get()
    val doc = LoadIntoPdfBox.load(fileAsStream)

    val countriesResult = DocToCountries.process(doc)
        .map(c => createCountryData(c, doc))

    val regionsResult = Regions.get
          .map(r => createRegionData(r, doc))

//    val countryDetails = DocToCountryDetails.process(doc)
//    val countryPopulationDetails = DocToCountryPopulationDetails.process(doc)
//
//    val aggregateDetails = CountryDetailsToAggregateDetail.convert(countryDetails, countryPopulationDetails)
//
//    val countryDetailsString = countryDetails.asJson.spaces2
//    val aggregateDetailsString = aggregateDetails.asJson.spaces2
//
//    WriteStringToFile.write(countryDetailsString, "countryDetails.json")
//    WriteStringToFile.write(aggregateDetailsString, "aggregateDetails.json")

//    println(s"Completed Processing ${countryDetails.size} entries")

    doc.close()
  }

  def createCountryData(country: Country, doc: PDDocument) : CountryData = {
    CountryData(
      name = country.name,
      region = country.region,
      isWenao = IsWenaoProcessor.isIt(country.name),
      population = PopulationProcessor.process(country, doc),
      wealthDetails = WealthDetailsProcessor.process(country, doc),
      wealthBracketDetails = WealthBracketsProcessor.process(country.name, doc)
    )
  }

  def createRegionData(region: String, doc: PDDocument) : RegionData = {
    RegionData(
      region = region,
      wealthBracketDetails = WealthBracketsProcessor.process(region, doc)
    )
  }
}
