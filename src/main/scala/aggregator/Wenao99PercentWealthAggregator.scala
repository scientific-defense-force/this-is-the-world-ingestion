package aggregator

import models.WealthBracketType
import models.country.CountryData

object Wenao99PercentWealthAggregator {
  def process(countryData: Vector[CountryData]) : Long = {
    val worldTotalWealth = countryData
      .map(_.wealthDetails.totalWeatlh)
      .sum

    val ninetyNinePercentTotalWealth = worldTotalWealth * .492

    val onePercentBracketTotal = countryData
      .filter(_.isWenao)
      .flatMap(_.wealthBracketDetails)
      .filter((wb) => wb.bracket == .01 && wb.bracketType == WealthBracketType.Top)
      .map(_.value)
      .sum

    val allPercentBracketTotal = countryData
      .filter(_.isWenao)
      .flatMap(_.wealthBracketDetails)
      .filter((wb) => wb.bracketType == WealthBracketType.Decile)
      .map(_.value)
      .sum

    val nintyNinePercentBracketTotal = allPercentBracketTotal - onePercentBracketTotal

    println("99 bracket total")
    println(nintyNinePercentBracketTotal)
    println("all percent")
    println(allPercentBracketTotal)
    println("one percent")
    println(onePercentBracketTotal)

    (ninetyNinePercentTotalWealth * (nintyNinePercentBracketTotal / 100)).toLong
  }
}
