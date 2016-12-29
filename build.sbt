lazy val root = (project in file(".")).
  settings(
    name := "entry",
    version := "1.0",
    scalaVersion := "2.11.8"
  )

libraryDependencies += "org.apache.pdfbox" % "pdfbox" % "latest.release"
libraryDependencies += "org.sorm-framework" % "sorm" % "latest.release" //blocking 2.12 https://github.com/sorm/sorm/issues/83
libraryDependencies += "mysql" % "mysql-connector-java" % "latest.release"

dependencyOverrides += "org.scala-lang" % "scala-compiler" % scalaVersion.value

//libraryDependencies += "org.apache.poi" % "poi" % "latest.release"
//libraryDependencies += "org.apache.poi" % "poi-ooxml" % "latest.release"
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
