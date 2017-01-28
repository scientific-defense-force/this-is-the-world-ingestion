import io.circe.generic.auto._
import io.circe.syntax._
import models.{Country, CountryData}
import org.apache.pdfbox.pdmodel.PDDocument
import processors.{IsWenao, Population, WealthDetails}

object Entry {


  def main(args: Array[String]) {

    val fileAsStream = RetrieveFileAsStream.get()
    val doc = LoadIntoPdfBox.load(fileAsStream)

    val result = DocToCountries.process(doc)
        .map(c => createCountryData(c, doc))

//    val ok = createCountryData(result.head, doc)

    println(result)

//    DocToCountries.process(doc)
//        .map(c => Coun)



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
  }

  def createCountryData(country: Country, doc: PDDocument) : CountryData = {
    CountryData(
      name = country.name,
      region = country.region,
      isWenao = IsWenao.isIt(country.name),
      population = Population.process(country, doc),
      wealthDetail = WealthDetails.process(country, doc)
    )
  }
}
