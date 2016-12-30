
object Entry {
  def main(args: Array[String]) {


    val fileAsStream = RetrieveFileAsStream.get()
    val doc = LoadIntoPdfBox.load(fileAsStream)

    val countryDetails = DocToCountryDetails.process(doc)

    val countryDetailsString = CountryDetailToJson.convert(countryDetails)

    WriteStringToFile.write(countryDetailsString, "countryDetails.json")

    println(s"Completed Processing ${countryDetails.size} entries")
  }
}
