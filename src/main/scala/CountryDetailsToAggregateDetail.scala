import models.{AggregateDetails, CountryDetail}

object CountryDetailsToAggregateDetail {
  def convert(countryDetails: Vector[CountryDetail]) : AggregateDetails = {
    val oecdTotal = countryDetails
                          .filter(_.isOecd)
                          .map(_.totalWeatlh)
                          .flatten
                          .sum

    val nonOecdTotal = countryDetails
                        .filterNot(_.isOecd)
                        .map(_.totalWeatlh)
                        .flatten
                        .sum

    AggregateDetails(otherTotalWealth = nonOecdTotal, oecdTotalWealth = oecdTotal)
  }
}
