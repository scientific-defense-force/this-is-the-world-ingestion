import java.io.{FileInputStream, InputStream}
import java.net.URL

object RetrieveFileAsStream {

  def get() : InputStream = {

//    val theStream = new URL("http://publications.credit-suisse.com/tasks/render/file/index.cfm?fileid=AD6F2B43-B17B-345E-E20A1A254A3E24A5").openStream()

    val theStream = new FileInputStream("global-wealth-databook-2016.pdf")

    theStream
  }
}