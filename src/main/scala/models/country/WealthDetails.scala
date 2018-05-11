package models.country

case class WealthDetails(incomeGroup: String,
                         gdpPerAdult2017: Option[Int],
                         wealthPerAdult2000: Int,
                         weatlhPerAdult2017: Int,
                         totalWeatlh: Long,
                         shareOfWorldWealth2017: Double
                        )
