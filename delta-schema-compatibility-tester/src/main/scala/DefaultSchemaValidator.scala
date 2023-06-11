package org.amishel.utils

class DefaultSchemaValidator(val currentSchemaLoader: SchemaLoader, val newSchemaLoader: SchemaLoader,
                             val formatter: SchemaValidationResultFormatter = new TreeStringFormatter,
                             val schemaMerger: SchemaMerger = new DeltaSchemaMerger
                            ) extends SchemaValidator {
  protected var result: SchemaValidationResult = _

  override def MergeSchemas(): Unit = {
    val currentSchema = currentSchemaLoader.loadAsSparkSchema()
    val newSchema = newSchemaLoader.loadAsSparkSchema()
    result = schemaMerger.MergeSchemas(currentSchema, newSchema)
  }

  override def Result: SchemaValidationResult = result

  override def ResultFormatted(): String = formatter.formatString(result)
}
