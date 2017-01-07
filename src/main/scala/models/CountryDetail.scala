package models

case class CountryDetail(name: String,
                         region: String,
                         incomeGroup: String,
                         gdpPerAdult2016: Option[Int],
                         wealthPerAdult2000: Int,
                         weatlhPerAdult2016: Int,
                         totalWeatlh: Option[Int],
                         shareOfWorldWealth2016: Double,
                         isOecd: Boolean
                        )