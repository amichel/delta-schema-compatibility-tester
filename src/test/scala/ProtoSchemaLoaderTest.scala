package org.amishel.utils

import org.scalatest.funsuite.AnyFunSuiteLike

class ProtoSchemaLoaderTest extends AnyFunSuiteLike {

  test("testLoadAsSparkSchema") {
    val descriptorPath = getClass.getResource("/protoc_descriptors/address.descr").getPath
    val target = new ProtoSchemaLoader(descriptorPath, "Address")
    val actual = target.loadAsSparkSchema()
    assert {
      actual.toDDL == "country STRING,line_1 STRING,postal_code INT"
    }
  }
}
