import java.io.InputStream

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper

object LoadIntoPdfBox {
  def load(inputStream: InputStream): PDDocument = {

    PDDocument.load(inputStream)
  }
}
