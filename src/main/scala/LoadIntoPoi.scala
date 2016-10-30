import java.io.InputStream

import org.apache.poi.ss.usermodel.{Workbook, WorkbookFactory}

object LoadIntoPoi {
  def load(inputStream: InputStream) : Workbook = {

    WorkbookFactory.create(inputStream)
  }
}
