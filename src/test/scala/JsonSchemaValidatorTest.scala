package org.amishel.utils

import org.scalatest.funsuite.AnyFunSuiteLike

import java.nio.file.{FileSystems, Paths}

class JsonSchemaValidatorTest extends AnyFunSuiteLike {

  test("testJsonValidator") {
    val fileSeparator = FileSystems.getDefault.getSeparator
    val basePath = Paths.get(System.getProperty("user.dir"), "target", "scala-2.13", "test-classes", "json") + fileSeparator

    val testDefinition = Array(
      //TODO: for some reason loading resources via getClass.getResource() or getClassLoader does not find the descriptor
      ("person_v1.schema.json", "person_v2_compatible_with_v1.schema.json", true),
      ("person_v2_compatible_with_v1.schema.json", "person_v2.1_incompatible_with_v2.schema.json", false),
      ("person_v2_compatible_with_v1.schema.json", "person_v2.2_incompatible_with_v2.schema.json", false),
      ("person_v2_compatible_with_v1.schema.json", "person_v2.3_incompatible_with_v2.schema.json", false),
      ("person_v2_compatible_with_v1.schema.json", "person_v2.4_incompatible_with_v2.schema.json", false))

    testDefinition.foreach(t => {
      val target = new JsonSchemaValidator(basePath + t._1, basePath + t._2)
      target.MergeSchemas()
      assert(target.Result().Success == t._3)
    })
  }
}
