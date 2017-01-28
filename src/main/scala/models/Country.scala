package models

//case class Region(name: String)

case class Country(name: String, region: String)

case class CountryData(
  name: String,
  region: String,
  isWenao: Boolean,
  wealthDetail: WealthDetail,
//  wealthBracketDetails: WealthBracketDetails,
  population: Long
)

case class WealthDetail(incomeGroup: String,
                        gdpPerAdult2016: Option[Int],
                        wealthPerAdult2000: Int,
                        weatlhPerAdult2016: Int,
                        totalWeatlh: Long,
                        shareOfWorldWealth2016: Double
                        )
//
//case class CountryDetails(
//   country: Country,
//   wealthDetails: WealthDetails,
//   wealthBracketDetails: WealthBracketDetails,
//   population: Long
//)


