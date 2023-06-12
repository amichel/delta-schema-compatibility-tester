package org.amishel.utils

import org.apache.spark.sql.types.StructType

class SchemaValidationResult(var Success: Boolean = false, var Message: String = "",
                             var CurrentSchema: Option[StructType] = None, var NewSchema: Option[StructType] = None,
                             var MergedSchema: Option[StructType] = None) {
}


