package org.amishel.utils

import com.google.protobuf.DescriptorProtos.FileDescriptorSet
import com.google.protobuf.Descriptors.{Descriptor, FileDescriptor}
import org.apache.spark.sql.protobuf.utils.SchemaConverter
import org.apache.spark.sql.types.StructType

import java.io.FileInputStream


class ProtoSchemaLoader(val filePath: String, val messageType: String = "") extends SchemaLoader {
  private def loadProtoDescriptor(): FileDescriptor = {
    val fileStream = new FileInputStream(filePath)
    val fileContent = try fileStream.readAllBytes() finally fileStream.close()

    val fileDescriptorSet = FileDescriptorSet.parseFrom(fileContent)
    val fileDescriptorProto = fileDescriptorSet.getFile(0)
    val dependencies = Array.empty[FileDescriptor]
    val fileDescriptor = FileDescriptor.buildFrom(fileDescriptorProto, dependencies)

    fileDescriptor
  }

  def loadAsSparkSchema(): StructType = {
    val descriptor: Descriptor = if (messageType.isEmpty)
      loadProtoDescriptor().getMessageTypes.get(0)
    else
      loadProtoDescriptor().findMessageTypeByName(messageType)

    SchemaConverter.toSqlType(descriptor).dataType.asInstanceOf[StructType]
  }
}
