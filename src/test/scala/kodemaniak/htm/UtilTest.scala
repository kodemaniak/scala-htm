package kodemaniak.htm

import org.scalatest.{Matchers, WordSpec}

/**
  * Created by carsten on 13.08.16.
  */
class UtilTest extends WordSpec with Matchers {
  "The SDR utils" should {
    "correctly compute the number of buckets" in {
      Util.numberOfBuckets(12, 3) should equal (10)
    }
  }
}
