package $package$

/**
 * Everyone's favourite wordcount example.
 */

import org.apache.spark.rdd._

object WordCount {

  /**
   * A slightly more complex than normal wordcount example with optional
   * separators and stopWords. Splits on the provided separators, removes
   * the stopwords, and converts everything to lower case.
   */
  def withStopWordsFiltered(
      rdd: RDD[String],
      separators: Array[Char] = " ".toCharArray,
      stopWords: Set[String] = Set("the")
  ): RDD[(String, Int)] = {

    val tokens: RDD[String]      = rdd.flatMap(_.split(separators).map(_.trim.toLowerCase))
    val lcStopWords: Set[String] = stopWords.map(_.trim.toLowerCase)
    val words: RDD[String] = tokens.filter(token => !lcStopWords.contains(token) && token.nonEmpty)
    val wordPairs: RDD[(String, Int)]  = words.map((_, 1))
    val wordCounts: RDD[(String, Int)] = wordPairs.reduceByKey(_ + _)
    wordCounts
  }
}
