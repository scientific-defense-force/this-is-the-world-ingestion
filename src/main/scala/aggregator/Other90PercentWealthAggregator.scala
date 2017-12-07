package aggregator

import models.WealthBracketType
import models.country.CountryData

object Other90PercentWealthAggregator {
  def process(countryData: Vector[CountryData]) : Long = {
    val worldTotalWealth = countryData
      .map(_.wealthDetails.totalWeatlh)
      .sum

    val tenPercentTotalWealth = worldTotalWealth * .8709

    val bracketTotal = countryData
      .filterNot(_.isWenao)
      .flatMap(_.wealthBracketDetails)
      .filter((wb) => wb.bracket == 1 && wb.bracketType == WealthBracketType.Decile)
      .map(_.value)
      .sum

    val otherTenPercentTotal = (tenPercentTotalWealth * (bracketTotal / 100)).toLong

    val otherCountryWealth = countryData
      .filterNot(_.isWenao)
      .map(_.wealthDetails.totalWeatlh)
      .sum

    otherCountryWealth - otherTenPercentTotal
  }
}
