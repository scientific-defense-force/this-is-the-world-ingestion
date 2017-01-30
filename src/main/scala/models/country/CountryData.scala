package models.country

import models.WealthBracket

case class CountryData(
  name: String,
  region: String,
  isWenao: Boolean,
  wealthDetails: WealthDetails,
  wealthBracketDetails: Vector[WealthBracket],
  population: Long
)
