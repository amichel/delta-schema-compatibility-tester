package org.amishel.utils

import org.scalatest.funsuite.AnyFunSuiteLike

import java.nio.file.{FileSystems, Paths}

class ProtoSchemaValidatorTest extends AnyFunSuiteLike {

  test("testProtoValidator") {
    val fileSeparator = FileSystems.getDefault.getSeparator
    val descriptorBasePath = Paths.get(System.getProperty("user.dir"), "target", "scala-2.13", "resource_managed", "test", "protoc_descriptors") + fileSeparator

    val testDefinition = Array(
      //TODO: for some reason loading resources via getClass.getResource() or getClassLoader does not find the descriptor
      ("person_v1.descr", "person_v2_compatible_with_v1.descr", true),
      ("person_v2_compatible_with_v1.descr", "person_v2.1_incompatible_with_v2.descr", false),
      ("person_v2_compatible_with_v1.descr", "person_v2.2_incompatible_with_v2.descr", false),
      ("person_v2_compatible_with_v1.descr", "person_v2.3_incompatible_with_v2.descr", false),
      ("person_v2_compatible_with_v1.descr", "person_v2.4_incompatible_with_v2.descr", false))

    testDefinition.foreach(t => {
      val target = new ProtoSchemaValidator(descriptorBasePath + t._1, descriptorBasePath + t._2, "Person")
      target.MergeSchemas()
      assert(target.Result().Success == t._3)
    })
  }
}
