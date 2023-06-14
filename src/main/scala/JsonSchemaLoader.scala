package org.amishel.utils

import org.apache.spark.sql.types.StructType

import java.io.FileInputStream

class JsonSchemaLoader(val filePath: String) extends SchemaLoader {
  def loadAsSparkSchema(): StructType = {
    val fileStream = new FileInputStream(filePath)
    val fileContent = try fileStream.readAllBytes() finally fileStream.close()
    JsonSchemaConverter.convertContent(new String(fileContent))
  }
}
