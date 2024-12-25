package $package$

/**
 * A simple test for everyone's favourite wordcount example.
 */

import com.holdenkarau.spark.testing.SharedSparkContext
import org.apache.spark.rdd.RDD
import org.scalatest.flatspec.AnyFlatSpecLike
import org.scalatest.matchers.should.Matchers

class WordCountTest extends AnyFlatSpecLike with Matchers with SharedSparkContext {

  "WordCount" should "count words with Stop Words Removed" in {
    val linesRDD: RDD[String] = sc.parallelize(
      Seq(
        "How happy was the panda? You ask.",
        "Panda is the most happy panda in all the#!?ing land!"
      )
    )

    val stopWords: Set[String]   = Set("a", "the", "in", "was", "there", "she", "he")
    val splitTokens: Array[Char] = "#%?!. ".toCharArray

    val wordCounts: RDD[(String, Int)] =
      WordCount.withStopWordsFiltered(linesRDD, splitTokens, stopWords)
    val wordCountsAsMap: collection.Map[String, Int] = wordCounts.collectAsMap()

    assert(!wordCountsAsMap.contains("the"))
    assert(!wordCountsAsMap.contains("?"))
    assert(!wordCountsAsMap.contains("#!?ing"))
    assert(wordCountsAsMap.contains("ing"))
    wordCountsAsMap("panda") should be(3)
  }
}
