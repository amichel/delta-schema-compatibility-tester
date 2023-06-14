package org.amishel.utils

import org.apache.avro.Schema
import org.apache.spark.sql.avro.SchemaConverters
import org.apache.spark.sql.types.StructType

import java.io.FileInputStream

class AvroSchemaLoader(val filePath: String) extends SchemaLoader {
  def loadAsSparkSchema(): StructType = {
    val fileStream = new FileInputStream(filePath)
    val schema = try new Schema.Parser().parse(fileStream) finally fileStream.close()
    SchemaConverters.toSqlType(schema).dataType.asInstanceOf[StructType]
  }
}
