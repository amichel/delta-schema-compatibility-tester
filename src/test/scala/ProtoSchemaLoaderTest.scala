package org.amishel.utils

import org.scalatest.funsuite.AnyFunSuiteLike

class ProtoSchemaLoaderTest extends AnyFunSuiteLike {

  test("testLoadAsSparkSchema") {
    //TODO: for some reason loading resources via getClass.getResource() or getClassLoader does not find the descriptor
    val descriptorPath = s"${System.getProperty("user.dir")}\\target\\scala-2.13\\resource_managed\\test\\protoc_descriptors\\address.descr"
    val target = new ProtoSchemaLoader(descriptorPath, "Address")
    val actual = target.loadAsSparkSchema()
    assert {
      actual.toDDL == "country STRING,line_1 STRING,postal_code INT"
    }
  }
}
