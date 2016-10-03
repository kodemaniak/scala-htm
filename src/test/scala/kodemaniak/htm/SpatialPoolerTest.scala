package kodemaniak.htm

import org.scalatest.{Matchers, WordSpec}

/**
  * Created by carsten on 18.09.16.
  */
class SpatialPoolerTest extends WordSpec with Matchers {
  "The SpatialPooler" should {
    "initialize its connections correctly" in {
      val sp = SpatialPooler(100, 100, 0.1)
      sp.connections should have size (100)
      sp.connections foreach (c => c should have size (10))
      val colsPerInput = sp.connections.flatMap(identity).map(_.input).groupBy(identity).map(_._2.size)
      val sum = colsPerInput.sum
      val avg = sum.toDouble / colsPerInput.size
      println(avg)
      (avg > 9 && avg < 11) should be (true)
    }

    "process a SDR and activate the correct columns" in {

    }
  }
}
