lazy val root = (project in file(".")).
  settings(
    name := "entry",
    version := "1.0",
    scalaVersion := "2.12.1"
  )

libraryDependencies += "org.apache.pdfbox" % "pdfbox" % "latest.release"

val circeVersion = "latest.release"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic"
//  "io.circe" %% "circe-parser"
).map(_ % circeVersion)