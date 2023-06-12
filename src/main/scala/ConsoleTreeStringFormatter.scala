package org.amishel.utils

import org.apache.spark.sql.types.StructType

import scala.Console.{GREEN, RED, RESET, YELLOW}

class ConsoleTreeStringFormatter extends SchemaValidationResultFormatter {
  override def formatString(result: SchemaValidationResult): String = new StringBuilder()
    .append(s"Success:")
    .append(s"$RESET${if (result.Success) GREEN else RED}${result.Success}$RESET\n")
    .append(s"Message:")
    .append(s"$RESET${if (result.Success) GREEN else RED}${result.Message}$RESET\n")
    .append(s"Current:\n${result.CurrentSchema.getOrElse(new StructType).treeString}\n")
    .append(s"New:\n${result.NewSchema.getOrElse(new StructType).treeString}\n")
    .append(s"Merged:\n$YELLOW${result.MergedSchema.getOrElse(new StructType).treeString}$RESET")
    .toString
}
