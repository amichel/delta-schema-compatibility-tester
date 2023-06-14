package org.amishel.utils

class JsonSchemaValidator(currentSchemaPath: String, newSchemaPath: String,
                          override val formatter: SchemaValidationResultFormatter = new TreeStringFormatter)
  extends DefaultSchemaValidator(new JsonSchemaLoader(currentSchemaPath),
    new JsonSchemaLoader(newSchemaPath), formatter)