package org.amishel.utils

class AvroSchemaValidator(currentSchemaPath: String, newSchemaPath: String,
                          override val formatter: SchemaValidationResultFormatter = new TreeStringFormatter)
  extends DefaultSchemaValidator(new AvroSchemaLoader(currentSchemaPath),
    new AvroSchemaLoader(newSchemaPath), formatter)