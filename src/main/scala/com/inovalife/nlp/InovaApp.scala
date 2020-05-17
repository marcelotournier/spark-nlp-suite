package com.inovalife.nlp

import com.inovalife.nlp.utils.SparkApp
import com.databricks.spark.corenlp.functions._
import org.apache.spark.sql.functions._

/**
 * @author marcelotournier
 */
object InovaApp extends App with SparkApp {
  import spark.implicits._

  val inputDF = Seq(
    (1, "<xml>Stanford University is located in California. It is a great university for 11.6 billion Dollars.</xml>")
  ).toDF("id", "text")

  val output = inputDF.
    select(cleanxml('text).as('doc)).
    select(explode(ssplit('doc)).as('sen)).
    select('sen, tokenize('sen).as('words)).
    cache()

  output.show(truncate = false)

  // val processedOutput = output.select(ner('sen).as('nerTags)).cache() // TODO: Fix OOM Error for NER Tagger!
  val processedOutput = output.select(sentiment('sen).as('sentiment))

  processedOutput.show(false)
}
