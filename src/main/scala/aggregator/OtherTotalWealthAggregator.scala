package aggregator

import models.country.CountryData

object OtherTotalWealthAggregator {
  def process(countryData: Vector[CountryData]): Long = {
    countryData
      .filterNot(_.isWenao)
      .map(_.wealthDetails.totalWeatlh)
      .sum
  }
}
