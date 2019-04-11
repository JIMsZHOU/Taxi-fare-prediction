name := """CSYE7200_FinalProject"""
organization := "csye7200"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.9"


// Adds additional packages into Twirl
//TwirlKeys.templateImports += "csye7200.controllers._"
// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "csye7200.binders._" 


dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-core" % "2.9.7"
dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-databind" % "2.9.7"
dependencyOverrides += "com.fasterxml.jackson.module" % "jackson-module-scala_2.11" % "2.9.7"


libraryDependencies ++= Seq(
  jdbc,
  ehcache,
  ws,
  specs2 % Test,
  guice,
  "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.1" % Test,
  "org.apache.spark" %% "spark-core" % "2.4.0",
  "org.apache.spark" %% "spark-sql" % "2.4.0",
  "org.apache.spark" %% "spark-mllib" % "2.4.0",
  "org.apache.spark" %% "spark-streaming" % "2.4.0",
  "org.scala-lang" % "scala-compiler" % "2.11.9"
)