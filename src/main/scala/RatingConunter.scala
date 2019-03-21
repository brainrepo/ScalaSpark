package com.brainrepo.spark

import org.apache.spark._
import org.apache.spark.sql
import org.apache.spark.SparkContext._
import org.apache.log4j._



object RatingConunter {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.ERROR)

    val sc = new SparkContext("local", "Rating Count")

    val lines = sc.textFile("src/main/resources/u.data")

    val ratings = lines.map(_.toString.split("\t")(2))
    val results = ratings.countByValue()

    val sortedResouts = results.toSeq.sortBy(_._1)
    sortedResouts.foreach(println)
  }
}
