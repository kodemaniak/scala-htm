package kodemaniak.htm

import org.scalatest.{Matchers, WordSpec}

import scala.collection.SortedSet

/**
  * Created by carsten on 07.08.16.
  */
class ScalarEncoderTest extends WordSpec with Matchers {

  val given = afterWord("given")

  "The ScalarEncoder" should {
    "throw exception when w is not odd" in {
      intercept[IllegalArgumentException] {
        ScalarEncoder(0, 100, 12, 2)
      }
    }
    "throw exception when min >= max is not odd" in {
      intercept[IllegalArgumentException] {
        ScalarEncoder(100, 100, 12, 3)
      }
    }
    "encode all values equally with n = 1 and w = 1" in {
      val encoder = ScalarEncoder(0, 100, 1, 1)
      val encodings = (-1000 to 1000) map (i => encoder.encode(i))
      encodings forall (_.bits.size == 1) should be (true)
      encodings forall (_.bits.head == 1) should be (true)
    }
    "encode correctly with min/max = 0/100, n = 12, w = 3" in {
      val encoder = ScalarEncoder(0, 100, 12, 3)
      encoder.encode(0) should be (SDR(1, 2, 3))
      encoder.encode(7) should be (SDR(1, 2, 3))
      encoder.encode(15) should be (SDR(2, 3, 4))
      encoder.encode(36) should be (SDR(4, 5, 6))
      encoder.encode(100) should be (SDR(10, 11, 12))
    }
    "encode correctly with min/max = 0/100, n = 12, w = 5" in {
      val encoder = ScalarEncoder(0, 100, 12, 5)
      encoder.encode(1) should be (SDR(1, 2, 3, 4, 5))
      encoder.encode(7) should be (SDR(1, 2, 3, 4, 5))
      encoder.encode(15) should be (SDR(2, 3, 4, 5, 6))
      encoder.encode(36) should be (SDR(3, 4, 5, 6, 7))
    }
    "encode correctly with min/max = -10/10, n = 12, w = 3" in {
      val encoder = ScalarEncoder(-10, 10, 12, 3)
      encoder.encode(-10) should be (SDR(1, 2, 3))
      encoder.encode(-1) should be (SDR(5, 6, 7))
      encoder.encode(7) should be (SDR(9, 10, 11))
      encoder.encode(15) should be (SDR(10, 11, 12))
    }
  }
}
