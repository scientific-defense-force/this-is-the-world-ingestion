import java.io.{File, FileInputStream, InputStream}
import java.net.URL
import org.apache.commons.io.FileUtils

object RetrieveFileAsStream {

  def get() : InputStream = {

    if (!new File("global-wealth-databook-2016.pdf").exists()) {
      val theStream = new URL("http://publications.credit-suisse.com/tasks/render/file/index.cfm?fileid=AD6F2B43-B17B-345E-E20A1A254A3E24A5").openStream()

      val targetFile = new File("global-wealth-databook-2016.pdf")

      FileUtils.copyInputStreamToFile(theStream, targetFile)
    }

    new FileInputStream("global-wealth-databook-2016.pdf")
  }
}