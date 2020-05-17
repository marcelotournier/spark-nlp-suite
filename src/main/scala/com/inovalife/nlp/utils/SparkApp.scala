package com.inovalife.nlp.utils

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession

trait SparkApp {
  // Spark configuration defaults:
  val conf = new SparkConf().
    setAppName("spark").
    setMaster("local[*]")

  // These are the Spark Context and Spark Sessions
  val sc = new SparkContext(conf)
  val spark = SparkSession.builder.config(sc.getConf).getOrCreate()
}
