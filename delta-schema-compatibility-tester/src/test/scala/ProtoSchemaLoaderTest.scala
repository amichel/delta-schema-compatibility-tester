package org.amishel.utils

import org.scalatest.funsuite.AnyFunSuiteLike

class ProtoSchemaLoaderTest extends AnyFunSuiteLike {

  test("testLoadAsSparkSchema") {
    val target = new ProtoSchemaLoader("src/test/artifacts/proto/descriptors/address.proto", "Address")
    val actual = target.loadAsSparkSchema()
    assert {
      actual.toDDL == "country: STRING, line_1: STRING, postal_code: INT"
    }
  }
}
