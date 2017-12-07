package aggregator

import models.WealthBracketType
import models.country.CountryData

object Wenao1PercentWealthAggregator {
  // So the logic is that page 111-114 show what percentage country X makes up of what wealth bracket
  // eg. 37.8% of people in the 1% brackets are in the USA
  // Therefore we calculate the total 1% bracket and take the sum of the brackets of all the Wenao countries
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

    (onePercentTotalWealth * (bracketTotal / 100)).toLong
  }
}
