name := "FilmOmatic"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies += "com.typesafe.akka" %% "akka-agent" % "2.4.17"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"
libraryDependencies += "com.pi4j" % "pi4j-gpio-extension" % "1.1"

lazy val src = project
lazy val submodules = project

