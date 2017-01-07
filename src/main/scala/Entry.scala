import models.{AggregateDetails, CountryDetail}
import io.circe.generic.auto._
import io.circe.syntax._

object Entry {


  def main(args: Array[String]) {


    val fileAsStream = RetrieveFileAsStream.get()
    val doc = LoadIntoPdfBox.load(fileAsStream)

    val countryDetails = DocToCountryDetails.process(doc)

    val aggregateDetails = CountryDetailsToAggregateDetail.convert(countryDetails)

    val countryDetailsString = countryDetails.asJson.spaces2
    val aggregateDetailsString = aggregateDetails.asJson.spaces2

    WriteStringToFile.write(countryDetailsString, "countryDetails.json")
    WriteStringToFile.write(aggregateDetailsString, "aggregateDetails.json")

    println(s"Completed Processing ${countryDetails.size} entries")
  }
}
