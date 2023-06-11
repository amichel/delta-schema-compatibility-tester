package org.amishel.utils

import org.apache.spark.sql.AnalysisException
import org.apache.spark.sql.delta.schema.SchemaMergingUtils
import org.apache.spark.sql.types.StructType

abstract class SchemaMerger {
  def MergeSchemas(currentSchema: StructType, newSchema: StructType): SchemaValidationResult = {
    var result = new SchemaValidationResult
    try {
      result.CurrentSchema = Some(currentSchema)
      result.NewSchema = Some(newSchema)
      result.MergedSchema = Some(Merge(result.CurrentSchema.get, result.NewSchema.get))

      result.Success = true
      result.Message = "Successfully merged schemas"
    } catch {
      case e: AnalysisException =>
        result.Message = e.message;

      case e: Exception => throw e
    }
    result
  }
  protected def Merge(currentSchema: StructType, newSchema: StructType): StructType
}

class DeltaSchemaMerger extends SchemaMerger {
  override protected def Merge(currentSchema: StructType, newSchema: StructType): StructType
  = SchemaMergingUtils.mergeSchemas(currentSchema, newSchema)
}