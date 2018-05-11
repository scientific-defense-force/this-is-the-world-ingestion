package processors.country

object IsWenaoProcessor {

  private val oecdCountries = List("Australia", "Austria", "Belgium", "Canada", "Denmark", "Finland", "France", "Germany", "Greece", "Iceland", "Ireland", "Italy", "Japan", "Luxembourg", "Netherlands", "New Zealand", "Norway", "Portugal", "Spain", "Sweden", "Switzerland", "Turkey", "United Kingdom", "United States")

  def isIt(countryName: String): Boolean = {
    oecdCountries.contains(countryName)
  }
}
