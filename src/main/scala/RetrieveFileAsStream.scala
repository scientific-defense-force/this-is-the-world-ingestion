import java.io.{FileInputStream, InputStream}
import java.net.URL

object RetrieveFileAsStream {

  def get() : InputStream = {

//    val theStream = new URL("http://publications.credit-suisse.com/tasks/render/file/index.cfm?fileid=C26E3824-E868-56E0-CCA04D4BB9B9ADD5").openStream()

    val theStream = new FileInputStream("global-wealth-databook-2016.pdf")

    theStream
  }
}