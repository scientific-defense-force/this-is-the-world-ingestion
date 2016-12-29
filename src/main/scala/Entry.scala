
object Entry {
  def main(args: Array[String]) {


    val fileAsStream = RetrieveFileAsStream.get()
    val doc = LoadIntoPdfBox.load(fileAsStream)

    val countryDetails = DocToCountryDetails.process(doc)

    Db.transaction {
      countryDetails.foreach(cd => Db.save(cd))
    }

    println(s"Completed Processing $countryDetails.size entries")
  }
}
