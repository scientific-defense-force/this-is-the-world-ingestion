//import models.{AggregateDetails, CountryDetail, CountryPopulationDetail}
//import processors.CountryIsWenao
//
//object CountryDetailsToAggregateDetail {
//  def convert(countryDetails: Vector[CountryDetail], countryPopulationDetails: Vector[CountryPopulationDetail]) : AggregateDetails = {
//    val wenaoWealthTotal = countryDetails
//                          .filter((cd) => CountryIsWenao.isIt(cd.name))
//                          .map(_.totalWeatlh)
//                          .sum
//
//    val nonOecdWealthTotal = countryDetails
//                        .filterNot((cd) => CountryIsWenao.isIt(cd.name))
//                        .map(_.totalWeatlh)
//                        .sum
//
//    val wenaoPopulationTotal = countryPopulationDetails
//      .filter((cd) => CountryIsWenao.isIt(cd.name))
//      .map(_.population)
//      .sum
//
//    val nonOecdPopulationTotal = countryPopulationDetails
//      .filterNot((cd) => CountryIsWenao.isIt(cd.name))
//      .map(_.population)
//      .sum
//
//    AggregateDetails(
//      otherTotalWealth = nonOecdWealthTotal,
//      wenaoTotalWealth = wenaoWealthTotal,
//      otherTotalPopulation = nonOecdPopulationTotal,
//      wenaoTotalPopulation = wenaoPopulationTotal
//    )
//  }
//}
