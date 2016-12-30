import io.circe.generic.auto._
import io.circe.syntax._
import models.CountryDetail

object CountryDetailToJson {
  def convert(countryDetails: Vector[CountryDetail]) : String = {
    countryDetails.asJson.spaces2
  }
}
