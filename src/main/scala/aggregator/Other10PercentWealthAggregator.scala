package aggregator

import models.WealthBracketType
import models.country.CountryData

object Other10PercentWealthAggregator {
  def process(countryData: Vector[CountryData]) : Long = {
    val worldTotalWealth = countryData
        .map(_.wealthDetails.totalWeatlh)
        .sum

    val onePercentTotalWealth = worldTotalWealth * .891

    val bracketTotal = countryData
        .filterNot(_.isWenao)
        .flatMap(_.wealthBracketDetails)
        .filter((wb) => wb.bracket == 1 && wb.bracketType == WealthBracketType.Decile)
        .map(_.value)
        .sum

    (onePercentTotalWealth * (bracketTotal / 100)).toLong
  }
}
