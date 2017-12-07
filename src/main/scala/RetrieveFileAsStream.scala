import java.io.{File, FileInputStream, InputStream}
import java.net.URL
import org.apache.commons.io.FileUtils

object RetrieveFileAsStream {

  def get() : InputStream = {

    if (!new File("global-wealth-databook-2017.pdf").exists()) {
      val theStream = new URL("http://publications.credit-suisse.com/tasks/render/file/index.cfm?fileid=432759CA-0A73-57F6-04C67EF7EE506040").openStream()

      val targetFile = new File("global-wealth-databook-2017.pdf")

      FileUtils.copyInputStreamToFile(theStream, targetFile)
    }

    new FileInputStream("global-wealth-databook-2017.pdf")
  }
}