packAutoSettings

import sbt.Keys._
import sbt._

lazy val commonDependencies = Seq(
  "com.pi4j" % "pi4j-gpio-extension" % "1.1",
  "com.pi4j" % "pi4j-device" % "1.1"
)

lazy val `pi-ssd-1306-java` = (project in file("submodules/pi-ssd1306-java"))
  .settings(
    organization := "eu.ondryaso",
    name := "ssd1306",
    version := "1.0-SNAPSHOT",
    autoScalaLibrary := false,
    crossPaths := false,
    javaSource in Compile := baseDirectory.value / "src",
    libraryDependencies := commonDependencies
  )

lazy val `film-o-matic` = (project in file("."))
  .dependsOn(`pi-ssd-1306-java`)
  .settings(
    name := "Film-O-Matic",
    version := "1.0",
    organization := "com.bradbrok",
    scalaVersion := "2.11.8",
    libraryDependencies := commonDependencies ++ Seq(
      "com.typesafe.akka" %% "akka-agent" % "2.4.17",
      "org.scalactic" %% "scalactic" % "3.0.1",
      "org.scalatest" %% "scalatest" % "3.0.1" % "test"
    )
  )
