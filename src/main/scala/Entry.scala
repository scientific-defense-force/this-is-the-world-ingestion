
object Entry {
  def main(args: Array[String]) {


    val fileAsStream = RetrieveFileAsStream.get()
    val ooh = LoadIntoPdfBox.load(fileAsStream)

    val omg = DocToCountryDetails.process(ooh)

//    val wut = CountryDetailsToSql

    Db.save(omg.head)

    println(omg.length)

    println("Hi!")
  }
}
