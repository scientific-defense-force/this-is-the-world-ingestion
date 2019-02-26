package models.country

case class WealthDetails(incomeGroup: String,
                         gdpPerAdult2018: Option[Int],
                         wealthPerAdult2000: Int,
                         weatlhPerAdult2018: Int,
                         totalWeatlh: Long,
                         shareOfWorldWealth2018: Double
                        )
