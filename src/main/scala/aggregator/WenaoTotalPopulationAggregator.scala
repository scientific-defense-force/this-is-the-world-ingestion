package aggregator

import models.country.CountryData

object WenaoTotalPopulationAggregator {
  def process(countryData: Vector[CountryData]) : Long = {
    countryData
      .filter(_.isWenao)
      .map(_.population)
      .sum
  }
}
