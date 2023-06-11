package org.amishel.utils

trait SchemaValidationResultFormatter {
  def formatString(result: SchemaValidationResult): String
}