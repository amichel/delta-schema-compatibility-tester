package org.amishel.utils

import org.scalatest.funsuite.AnyFunSuiteLike

import java.nio.file.{FileSystems, Paths}

class JsonSchemaLoaderTest extends AnyFunSuiteLike {

  test("testLoadJsonAsSparkSchema") {
    val fileSeparator = FileSystems.getDefault.getSeparator
    val basePath = Paths.get(System.getProperty("user.dir"), "target", "scala-2.13", "test-classes", "json") + fileSeparator

    //TODO: for some reason loading resources via getClass.getResource() or getClassLoader does not find the descriptor
    val descriptorPath = basePath + "address.schema.json"
    val target = new JsonSchemaLoader(descriptorPath)
    val actual = target.loadAsSparkSchema()
    assert {
      actual.toDDL == "country BIGINT NOT NULL,line_1 STRING NOT NULL,postal_code BIGINT NOT NULL"
    }
  }
}
