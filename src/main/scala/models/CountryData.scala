package models

/**
  * Created by alistairburrowes on 2017/01/30.
  */
case class CountryData(
  name: String,
  region: String,
  isWenao: Boolean,
  wealthDetails: WealthDetails,
  wealthBracketDetails: Vector[WealthBracket],
  population: Long
)
