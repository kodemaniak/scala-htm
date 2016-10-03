package kodemaniak.htm

import scala.collection.SortedSet

/**
  * Created by carsten on 07.08.16.
  */
case class ScalarEncoder(min: Double, max: Double, n: Int, w: Int) {
  require(w % 2 != 0, "w must be an odd number.")
  require(min < max, "min must strictly be less than max.")

  private val numBuckets = Util.numberOfBuckets(n, w)
  private val bucketSize = (max - min) / numBuckets.toDouble
  private val buckets = (1 to numBuckets).map(i => i -> SortedSet((i to i + w - 1).map(identity): _*)).toMap

  def encode(value: Double): SDR = {
    // if value == max we have to fix the computed bucket index
    val bucket = math.min((cap(value) - min) / bucketSize + 1, numBuckets)
    SDR(buckets(bucket.toInt))
  }

  private def cap(value: Double): Double = {
    math.max(math.min(value, max), min)
  }
}
