package aggregator

import models.WealthBracketType
import models.country.CountryData

object Wenao99PercentWealthAggregator {
  def process(countryData: Vector[CountryData]) : Long = {
    val worldTotalWealth = countryData
      .map(_.wealthDetails.totalWeatlh)
      .sum

    val onePercentTotalWealth = worldTotalWealth * .508

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
