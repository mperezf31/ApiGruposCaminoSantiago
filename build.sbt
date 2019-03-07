name := """recipe-manager"""
organization := "com.mperezf"

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.12.6"

crossScalaVersions := Seq("2.12.6", "2.11.12")

libraryDependencies += "com.h2database" % "h2" % "1.4.194"

libraryDependencies += guice
libraryDependencies += evolutions
libraryDependencies += jdbc

libraryDependencies ++= Seq(
  ehcache
)