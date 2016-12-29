import models.CountryDetail
import sorm._

object Db extends Instance (
  entities = Set( Entity[CountryDetail]() ),
  url = s"jdbc:mysql://database/${sys.env("DATABASE_NAME")}",
  initMode = InitMode.DropCreate,
  user = sys.env("DATABASE_USER"),
  password = sys.env("DATABASE_PASSWORD")
)