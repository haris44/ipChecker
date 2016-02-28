import play.sbt.PlayImport._
import play.sbt.PlayScala
import play.sbt.routes.RoutesKeys._
import sbt.Keys._
import sbt._

name := """ipChecker"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  filters,
  specs2 % Test,
  "com.typesafe.play" %% "anorm" % "2.4.0",
  "mysql" % "mysql-connector-java" % "5.1.21"
)



resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
