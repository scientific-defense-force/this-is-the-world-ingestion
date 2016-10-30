import java.io.{ByteArrayInputStream, InputStream}
import java.nio.charset.StandardCharsets

/**
  * Created by alistairburrowes on 2016/10/29.
  */
object StringToInputStream {
  def convert(inputString: String) : InputStream = {
    new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8))
  }

}
