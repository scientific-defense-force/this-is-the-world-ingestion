import java.io.InputStream

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper

object LoadIntoPdfBox {
  def load(inputStream: InputStream) : PDDocument = {

    val doc = PDDocument.load(inputStream)

    val stripper = new PDFTextStripper()

//    val page = doc.getPage(23)
//
//    print(stripper.getText(doc))

    doc
  }
}
