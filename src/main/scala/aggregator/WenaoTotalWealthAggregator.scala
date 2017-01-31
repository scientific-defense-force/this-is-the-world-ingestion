package aggregator

import models.country.CountryData

object WenaoTotalWealthAggregator {
  def process(countryData: Vector[CountryData]) : Long = {
    countryData
      .filter(_.isWenao)
      .map(_.wealthDetails.totalWeatlh)
      .sum
  }
}
