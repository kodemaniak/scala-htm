package kodemaniak.htm

import scala.util.Random

/**
  * Created by carsten on 13.08.16.
  */
object SpatialPooler {
  // Initialize:
  // TODO: initial permanence in small range around connectedPermanence
  // TODO: each column has natural center over input, permanence has bias towards center
  def apply(inputWidth: Int, numberOfColumns: Int, potentialPercent: Double): SpatialPooler = {
    val potentialPoolSize = (inputWidth * potentialPercent).toInt
    val inputs = 0 to inputWidth - 1
    val connections = (0 to numberOfColumns - 1) map {
      col =>
        val potentialPool = Random.shuffle(inputs).take(potentialPoolSize).toIndexedSeq.sorted
        potentialPool.map(input => Connection(col, input, Random.nextDouble()))
    }
    new SpatialPooler(connections, 10)
  }
}

case class SpatialPooler(connections: IndexedSeq[Seq[Connection]], minOverlap: Int) {
  // TODO: Add boosting
  /**
    * Process an SDR arriving from input.
    *
    * @param sdr
    * @return
    */
  def process(sdr: SDR): SDR = {
    sdr
  }

  def overlap = ???
  def inhibition = ???
  def learn = ???
}

case class Connection(column: Int, input: Int, permanence: Double)