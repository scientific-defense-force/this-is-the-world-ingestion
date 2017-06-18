lazy val root = (project in file(".")).
  settings(
    name := "entry",
    version := "1.0",
    scalaVersion := "2.12.2"
  )

libraryDependencies += "org.apache.pdfbox" % "pdfbox" % "2.0.6"
libraryDependencies += "commons-io" % "commons-io" % "2.5"

val circeVersion = "0.8.0"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic"
).map(_ % circeVersion)