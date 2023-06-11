package org.amishel.utils

object Main {
  def main(args: Array[String]): Unit = {
    val basePath = "D:\\Projects\\opsguru\\talent\\opsguru-sourcecode\\php_producer_poc\\scala\\SchemaValidation\\src\\main\\protobuf"
    val currentPath = s"$basePath\\descriptors_v2.proto"
    val newPath = s"$basePath\\descriptors_v3.proto"
    val validator = new ProtobufSchemaValidator(currentPath, newPath, "Person", new ConsoleTreeStringFormatter())
    validator.MergeSchemas()
    if (!validator.Result.Success)
      println(validator.ResultFormatted())
  }
}
