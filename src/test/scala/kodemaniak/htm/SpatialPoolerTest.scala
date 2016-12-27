package kodemaniak.htm

import org.scalatest.{Matchers, WordSpec}

import scala.util.Random

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
      (avg > 9 && avg < 11) should be (true)
    }

    "process a SDR and activate the correct columns" in {
      // create connections for 10 incoming bits and 5 columns
      val c00 = Connection(0, 0, 0.8)
      val c02 = Connection(0, 0, 0.5)
      val c03 = Connection(0, 0, 0.1)
      val c10 = Connection(0, 0, 0.2)
      val c11 = Connection(0, 0, 0.5)
      val c13 = Connection(0, 0, 0.6)
      val sp = new SpatialPooler(IndexedSeq(Seq(c00, c02, c03), Seq(c10, c11, c13)), 10)
      val sdr1 = SDR(0, 2, 3)
      sp.process(sdr1)

    }
  }
}
