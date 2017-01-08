import models.{AggregateDetails, CountryDetail, CountryPopulationDetail}
import util.CountryIsOecd

object CountryDetailsToAggregateDetail {
  def convert(countryDetails: Vector[CountryDetail], countryPopulationDetails: Vector[CountryPopulationDetail]) : AggregateDetails = {
    val oecdWealthTotal = countryDetails
                          .filter((cd) => CountryIsOecd.isIt(cd.name))
                          .map(_.totalWeatlh)
                          .flatten
                          .sum

    val nonOecdWealthTotal = countryDetails
                        .filterNot((cd) => CountryIsOecd.isIt(cd.name))
                        .map(_.totalWeatlh)
                        .flatten
                        .sum

//    println(countryPopulationDetails)

    val oecdPopulationTotal = countryPopulationDetails
      .filter((cd) => CountryIsOecd.isIt(cd.name))
      .map(_.population)
      .sum

    val nonOecdPopulationTotal = countryPopulationDetails
      .filterNot((cd) => CountryIsOecd.isIt(cd.name))
      .map(_.population)
      .sum

    AggregateDetails(
      otherTotalWealth = nonOecdWealthTotal,
      oecdTotalWealth = oecdWealthTotal,
      otherTotalPopulation = nonOecdPopulationTotal,
      oecdTotalPopulation = oecdPopulationTotal
    )
  }
}
