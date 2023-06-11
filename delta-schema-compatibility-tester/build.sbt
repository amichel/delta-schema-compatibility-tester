ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.11"

lazy val root = (project in file("."))
  .settings(
    name := "delta-schema-compatibility-tester",
    idePackagePrefix := Some("org.amishel.utils")
  )

libraryDependencies ++= Seq(
  "org.apache.spark" % "spark-protobuf_2.13" % "3.4.0",
  "org.apache.spark" % "spark-sql_2.13" % "3.4.0",
  "io.delta" % "delta-core_2.13" % "2.4.0",
  "org.scalactic" %% "scalactic" % "3.2.15",
  "org.scalatest" %% "scalatest" % "3.2.15" % "test"
)