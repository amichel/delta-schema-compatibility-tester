package org.amishel.utils

import org.scalatest.funsuite.AnyFunSuiteLike

import java.nio.file.{FileSystems, Paths}

class ProtoSchemaLoaderTest extends AnyFunSuiteLike {

  test("testLoadAsSparkSchema") {
    val fileSeparator = FileSystems.getDefault.getSeparator
    val descriptorBasePath = Paths.get(System.getProperty("user.dir"), "target", "scala-2.13", "resource_managed", "test", "protoc_descriptors") + fileSeparator

    //TODO: for some reason loading resources via getClass.getResource() or getClassLoader does not find the descriptor
    val descriptorPath = descriptorBasePath + "address.descr"
    val target = new ProtoSchemaLoader(descriptorPath, "Address")
    val actual = target.loadAsSparkSchema()
    assert {
      actual.toDDL == "country STRING,line_1 STRING,postal_code INT"
    }
  }
}
