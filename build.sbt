name := """healthyHabits"""
organization := "com.raunakjodhawat"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.15"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test
libraryDependencies ++= Seq(
  "org.postgresql" % "postgresql" % "42.3.1",
  "com.typesafe.slick" %% "slick" % "3.3.3"
)
// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.raunakjodhawat.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.raunakjodhawat.binders._"
