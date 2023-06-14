package org.amishel.utils

import org.scalatest.funsuite.AnyFunSuiteLike

import java.nio.file.{FileSystems, Paths}

class AvroSchemaLoaderTest extends AnyFunSuiteLike {

  test("testLoadJsonAsSparkSchema") {
    val fileSeparator = FileSystems.getDefault.getSeparator
    val basePath = Paths.get(System.getProperty("user.dir"), "target", "scala-2.13", "test-classes", "avro") + fileSeparator

    //TODO: for some reason loading resources via getClass.getResource() or getClassLoader does not find the descriptor
    val descriptorPath = basePath + "address.avro.json"
    val target = new AvroSchemaLoader(descriptorPath)
    val actual = target.loadAsSparkSchema()
    assert {
      actual.toDDL == "country STRING NOT NULL,line_1 STRING NOT NULL,postal_code INT NOT NULL"
    }
  }
}
