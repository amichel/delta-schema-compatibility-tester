package org.amishel.utils

import org.apache.spark.sql.types.StructType

trait SchemaLoader {

  def loadAsSparkSchema(): StructType
}
