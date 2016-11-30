import java.io.InputStream
import java.net.URL

object RetrieveFileAsStream {

  def get() : InputStream = {

//    val html = Source.fromURL("http://www.wid.world/TopIncomes/service/DownloadPdfServlet?fileName=WID-report.xlsx")(io.Codec("UTF-8"))
//    val html = Source.fromURL("https://www.realestate.com.au/buy")(io.Codec("UTF-8"))

//    val theStream = new URL("http://www.wid.world/TopIncomes/service/DownloadPdfServlet?fileName=WID-report.xlsx").openStream()

    val theStream = new URL("http://publications.credit-suisse.com/tasks/render/file/index.cfm?fileid=C26E3824-E868-56E0-CCA04D4BB9B9ADD5").openStream()

    theStream
  }
}