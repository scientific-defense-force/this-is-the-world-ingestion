package aggregator

import models.country.CountryData

object OtherTotalPopulationAggregator {
  def process(countryData: Vector[CountryData]) : Long = {
    countryData
      .filterNot(_.isWenao)
      .map(_.population)
      .sum
  }
}
