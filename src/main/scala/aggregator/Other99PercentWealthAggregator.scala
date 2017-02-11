package aggregator

import models.WealthBracketType
import models.country.CountryData

object Other99PercentWealthAggregator {
  def process(countryData: Vector[CountryData]) : Long = {
    val worldTotalWealth = countryData
      .map(_.wealthDetails.totalWeatlh)
      .sum

    val onePercentTotalWealth = worldTotalWealth * .508

    val bracketTotal = countryData
      .filterNot(_.isWenao)
      .flatMap(_.wealthBracketDetails)
      .filter((wb) => wb.bracket == .01 && wb.bracketType == WealthBracketType.Top)
      .map(_.value)
      .sum

    val otherOnePercentTotal = (onePercentTotalWealth * (bracketTotal / 100)).toLong

    val otherCountryWealth = countryData
      .filterNot(_.isWenao)
      .map(_.wealthDetails.totalWeatlh)
      .sum

    otherCountryWealth - otherOnePercentTotal
  }
}