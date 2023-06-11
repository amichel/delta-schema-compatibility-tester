package org.amishel.utils

class DefaultSchemaValidator(val currentSchemaLoader: SchemaLoader, val newSchemaLoader: SchemaLoader,
                             val formatter: SchemaValidationResultFormatter = new TreeStringFormatter,
                             val schemaMerger: SchemaMerger = new DeltaSchemaMerger
                            ) extends SchemaValidator {
  protected var result: SchemaValidationResult = null

  override def MergeSchemas() = {
    val currentSchema = currentSchemaLoader.loadAsSparkSchema()
    val newSchema = newSchemaLoader.loadAsSparkSchema()
    result = schemaMerger.MergeSchemas(currentSchema, newSchema)
  }

  override def Result = result

  override def ResultFormatted() = formatter.formatString(result)
}
