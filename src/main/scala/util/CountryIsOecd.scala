package util

object CountryIsOecd {
  val oecdCountries = List("Australia", "Austria", "Belgium", "Canada", "Chile", "Czech Republic", "Denmark", "Estonia", "Finland", "France", "Germany", "Greece", "Hungary", "Iceland", "Ireland", "Israel", "Italy", "Japan", "Korea", "Luxembourg", "Mexico", "Netherlands", "New Zealand", "Norway", "Poland", "Portugal", "Slovak Republic", "Slovenia", "Spain", "Sweden", "Switzerland", "Turkey", "United Kingdom", "United States")

  def isIt(countryName: String) : Boolean = {
      oecdCountries.contains(countryName)
  }
}
