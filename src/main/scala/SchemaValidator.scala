package org.amishel.utils

trait SchemaValidator {
  def MergeSchemas(): Unit

  def Result: SchemaValidationResult

  def ResultFormatted(): String
}
