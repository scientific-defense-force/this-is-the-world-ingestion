import java.io.InputStream
import java.net.URL

object RetrieveFileAsStream {

  def get() : InputStream = {

//    val html = Source.fromURL("http://www.wid.world/TopIncomes/service/DownloadPdfServlet?fileName=WID-report.xlsx")(io.Codec("UTF-8"))
//    val html = Source.fromURL("https://www.realestate.com.au/buy")(io.Codec("UTF-8"))

    val theStream = new URL("http://www.wid.world/TopIncomes/service/DownloadPdfServlet?fileName=WID-report.xlsx").openStream()

    theStream
  }
}