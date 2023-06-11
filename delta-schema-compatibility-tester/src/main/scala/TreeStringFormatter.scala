package org.amishel.utils

import org.apache.spark.sql.types.StructType

class TreeStringFormatter extends SchemaValidationResultFormatter {
  override def formatString(result: SchemaValidationResult): String = new StringBuilder()
    .append(s"Success:${result.Success}\n")
    .append(s"Message:${result.Message}\n")
    .append(s"Current:\n${result.CurrentSchema.getOrElse(new StructType).treeString}\n")
    .append(s"New:\n${result.NewSchema.getOrElse(new StructType).treeString}\n")
    .append(s"Merged:\n${result.MergedSchema.getOrElse(new StructType).treeString}")
    .toString
}
