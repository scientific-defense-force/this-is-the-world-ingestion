package aggregator

import models.WealthBracketType
import models.country.CountryData

object Wenao99PercentWealthAggregator {
  // See Wenao1PercentWealthAggregator for details on this
  // It's far easier to calculate the 1% wealth then subtract that from total wealth to get the 99%
  // rather than attempt to build up then total of the 99% wealth brackets
  def process(countryData: Vector[CountryData]) : Long = {
    val worldTotalWealth = countryData
      .map(_.wealthDetails.totalWeatlh)
      .sum

    val onePercentTotalWealth = worldTotalWealth * .4916

    val bracketTotal = countryData
      .filter(_.isWenao)
      .flatMap(_.wealthBracketDetails)
      .filter((wb) => wb.bracket == .01 && wb.bracketType == WealthBracketType.Top)
      .map(_.value)
      .sum

    val wenaoOnePercentTotal = (onePercentTotalWealth * (bracketTotal / 100)).toLong

    val wenaoCountryWealth = countryData
      .filter(_.isWenao)
      .map(_.wealthDetails.totalWeatlh)
      .sum

    wenaoCountryWealth - wenaoOnePercentTotal
  }
}
