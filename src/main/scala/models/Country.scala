package models

//case class Region(name: String)

case class Country(name: String, region: String)

case class CountryData(
  name: String,
  region: String,
  isWenao: Boolean,
//  wealthDetails: WealthDetails,
//  wealthBracketDetails: WealthBracketDetails,
  population: Long
)
//
//case class CountryDetails(
//   country: Country,
//   wealthDetails: WealthDetails,
//   wealthBracketDetails: WealthBracketDetails,
//   population: Long
//)


