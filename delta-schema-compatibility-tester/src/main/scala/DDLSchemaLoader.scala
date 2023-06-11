package org.amishel.utils

import org.apache.spark.sql.types.StructType
class DDLSchemaLoader(val ddl: String) extends SchemaLoader {
  def loadAsSparkSchema(): StructType = StructType.fromDDL(ddl)
}
