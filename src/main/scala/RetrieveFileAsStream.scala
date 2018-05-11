import java.io.{File, FileInputStream, InputStream}
import java.net.URL
import org.apache.commons.io.FileUtils

object RetrieveFileAsStream {

  private val publicationKey: String = "FB790DB0-C175-0E07-787A2B8639253D5A"
  private val fileName: String = s"global-wealth-databook-2017-$publicationKey.pdf"

  def get(): InputStream = {

    if (!new File(fileName).exists()) {
      val theStream = new URL(s"https://publications.credit-suisse.com/tasks/render/file/index.cfm?fileid=$publicationKey").openStream()

      val targetFile = new File(fileName)

      FileUtils.copyInputStreamToFile(theStream, targetFile)
    }

    new FileInputStream(fileName)
  }
}