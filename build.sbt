import sbt._
import Keys._

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-agent" % "2.4.17",
  "org.scalactic" %% "scalactic" % "3.0.1",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "com.pi4j" % "pi4j-gpio-extension" % "1.1"
)

lazy val commonSettings = Seq(
  name := "FilmOmatic",
  version := "1.0",
  organization := "com.bradbrok",

  scalaVersion := "2.11.8",
  libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-agent" % "2.4.17",
  "org.scalactic" %% "scalactic" % "3.0.1",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "com.pi4j" % "pi4j-gpio-extension" % "1.1"
)

)

lazy val src = (project in file("src"))
  .settings(commonSettings: _*)
lazy val submodules = (project in file("submodules"))
  .settings(commonSettings: _*)