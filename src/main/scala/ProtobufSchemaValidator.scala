package org.amishel.utils
class ProtobufSchemaValidator(currentDescriptorPath: String, newDescriptorPath: String, messageType: String,
                              override val formatter: SchemaValidationResultFormatter = new TreeStringFormatter)
  extends DefaultSchemaValidator(new ProtoSchemaLoader(currentDescriptorPath, messageType),
    new ProtoSchemaLoader(newDescriptorPath, messageType), formatter)