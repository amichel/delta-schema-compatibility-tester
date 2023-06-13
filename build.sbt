import java.nio.file.Files
import scala.collection.JavaConverters.*
import scala.sys.process.*

ThisBuild / version := "0.1.0"

ThisBuild / scalaVersion := "2.13.11"

lazy val root = (project in file("."))
  .settings(
    name := "delta-schema-compatibility-tester",
    idePackagePrefix := Some("org.amishel.utils"),
    testFrameworks += new TestFramework("org.scalatest.tools.Framework"),
    Test / testOptions += Tests.Argument(TestFrameworks.ScalaTest, "-oD"),
    Test / resourceGenerators += generateDescriptorsTask.taskValue,
    Test / managedResourceDirectories += (Test / resourceManaged).value
  )

libraryDependencies ++= Seq(
  "org.apache.spark" % "spark-protobuf_2.13" % "3.4.0",
  "org.apache.spark" % "spark-sql_2.13" % "3.4.0",
  "io.delta" % "delta-core_2.13" % "2.4.0",
  "org.scalactic" %% "scalactic" % "3.2.15",
  "org.scalatest" %% "scalatest" % "3.2.15" % "test",
  "com.lihaoyi" % "mainargs_2.13" % "0.5.0"
)

lazy val generateDescriptorsTask = taskKey[Seq[File]]("Generates descriptors using protoc")

generateDescriptorsTask := {
  val protoDir = (Test / resourceDirectory).value / "proto"
  val outputDir = (Test / resourceManaged).value / "protoc_out"
  val descriptorsDir = (Test / resourceManaged).value / "protoc_descriptors"
  Files.createDirectories(Path(outputDir).asPath)
  Files.createDirectories(Path(descriptorsDir).asPath)

  val protoFiles = (protoDir ** "*.proto").get
  val log = streams.value.log

  val descriptorFiles = protoFiles.flatMap { protoFile =>
    val descriptorFile = descriptorsDir / protoFile.getName.replace(".proto", ".descr")
    val command = s"protoc --proto_path=${protoDir} --java_out=$outputDir  ${protoFile.getName}  --include_imports --descriptor_set_out=${descriptorFile}"

    log.info(command)
    val exitCode = command.!

    if (exitCode == 0) {
      Some(protoFile)
    } else {
      throw new RuntimeException(s"protoc command failed for file: $protoFile")
    }
  }

  descriptorFiles
}