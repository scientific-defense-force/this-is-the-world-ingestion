//object Region extends Enumeration {
//  type Region = Value
//  val Mon, Tue, Wed, Thu, Fri, Sat, Sun = Value
//}

case class CountryDetail(name: String,
                          region: String,
                          incomeGroup: String,
                          gdpPerAdult2016: Int,
                          wealthPerAdult2000: Int,
                          weatlhPerAdult2016: Int,
                          totalWeatlh: Int,
                          shareOfWorldWealth2016: Double)

//case class Statistic(id: Short, name: String)
//case class CountryStatistic(country: Country, statistic: Statistic, value: Double)

//case class