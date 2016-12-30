import java.io.{File, PrintWriter}

object WriteStringToFile {
  def write(input: String, filename: String) : Unit = {
    val pw = new PrintWriter(new File(s"dist/$filename" ))
    pw.write(input)
    pw.close
  }
}
