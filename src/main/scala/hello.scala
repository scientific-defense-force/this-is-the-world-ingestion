
object Entry {
  def main(args: Array[String]) {


    val fileAsStream = RetrieveFileAsStream.get()
    val ooh = LoadIntoPdfBox.load(fileAsStream)


    println("Hi!")
  }
}
