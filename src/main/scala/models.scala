case class Country(id: Short, name: String)
case class Statistic(id: Short, name: String)
case class CountryStatistic(country: Country, statistic: Statistic, value: Double)