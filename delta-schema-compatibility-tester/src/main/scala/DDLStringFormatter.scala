package org.amishel.utils

import org.apache.spark.sql.types.StructType

class DDLStringFormatter extends SchemaValidationResultFormatter {
  override def formatString(result: SchemaValidationResult): String = new StringBuilder()
    .append(s"Success:${result.Success}\n")
    .append(s"Message:${result.Message}\n")
    .append(s"Current:${result.CurrentSchema.getOrElse(new StructType).toDDL}\n")
    .append(s"New:${result.NewSchema.getOrElse(new StructType).toDDL}\n")
    .append(s"Merged:${result.MergedSchema.getOrElse(new StructType).toDDL}\n")
    .toString
}
