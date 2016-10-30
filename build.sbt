lazy val root = (project in file(".")).
  settings(
    name := "hello",
    version := "1.0",
    scalaVersion := "2.12.0"
  )

libraryDependencies += "org.apache.poi" % "poi" % "latest.release"
libraryDependencies += "org.apache.poi" % "poi-ooxml" % "latest.release"
//libraryDependencies += "co.fs2" %% "fs2-core" % "0.9.1"
//libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.2.6"

//lazy val http4sVersion = "0.14.10a"

//// Only necessary for SNAPSHOT releases
//resolvers += Resolver.sonatypeRepo("snapshots")
//
//libraryDependencies ++= Seq(
//  "org.http4s" %% "http4s-dsl" % http4sVersion,
//  "org.http4s" %% "http4s-blaze-client" % http4sVersion
//)
