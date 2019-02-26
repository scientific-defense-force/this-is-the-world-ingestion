import java.io.{File, FileInputStream, InputStream}
import java.net.URL

import org.apache.commons.io.FileUtils

object RetrieveFileAsStream {

  private val fileName: String = s"global-wealth-databook-2018.pdf"

  def get(): InputStream = {

    if (!new File(fileName).exists()) {
      val theStream = new URL(s"https://www.credit-suisse.com/media/assets/corporate/docs/about-us/research/publications/$fileName").openStream()

      val targetFile = new File(fileName)

      FileUtils.copyInputStreamToFile(theStream, targetFile)
    }

    new FileInputStream(fileName)
  }
}
