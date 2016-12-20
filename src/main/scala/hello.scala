
object Entry {
  def main(args: Array[String]) {


    val fileAsStream = RetrieveFileAsStream.get()
    val ooh = LoadIntoPdfBox.load(fileAsStream)
    val omg = DocToCountryDetails.process(ooh)

    println(omg.length)

    println("Hi!")
  }
}
