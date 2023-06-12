package org.amishel.utils

class DDLSchemaValidator(currentSchemaDDL: String, newSchemaDDL: String,
                         override val formatter: SchemaValidationResultFormatter = new TreeStringFormatter)
  extends DefaultSchemaValidator(new DDLSchemaLoader(currentSchemaDDL),
    new DDLSchemaLoader(newSchemaDDL), formatter)