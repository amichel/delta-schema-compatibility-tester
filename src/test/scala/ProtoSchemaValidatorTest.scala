package org.amishel.utils

import org.scalatest.funsuite.AnyFunSuiteLike

class ProtoSchemaValidatorTest extends AnyFunSuiteLike {

  test("testProtoValidator") {
    //TODO: for some reason loading resources via getClass.getResource() or getClassLoader does not find the descriptor
    val descriptorBasePath = s"${System.getProperty("user.dir")}\\target\\scala-2.13\\resource_managed\\test\\protoc_descriptors\\"

    val testDefinition = Array(
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
