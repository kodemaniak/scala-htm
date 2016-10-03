package kodemaniak.htm

import scala.util.Random

/**
  * Created by carsten on 13.08.16.
  */
object SpatialPooler {
  def apply(inputWidth: Int, numberOfColumns: Int, potentialPercent: Double): SpatialPooler = {
    val potentialPoolSize = (inputWidth * potentialPercent).toInt
    val inputs = (0 to inputWidth - 1)
    val connections = (0 to numberOfColumns - 1) map {
      col =>
        val potentialPool = Random.shuffle(inputs).take(potentialPoolSize).toIndexedSeq.sorted
        potentialPool.map(input => Connection(col, input, Random.nextDouble()))
    }
    new SpatialPooler(connections)
  }
}

class SpatialPooler(val connections: IndexedSeq[Seq[Connection]]) {
  def process(sdr: SDR): SDR = {
    sdr
  }
}

case class Connection(column: Int, input: Int, permanence: Double)