package kodemaniak.htm

import scala.collection.SortedSet

/**
  * Created by carsten on 05.06.16.
  */
object SDR {
  def apply(idxs: Int*): SDR = {
    SDR(SortedSet(idxs: _*))
  }
}

case class SDR(bits: SortedSet[Int])
