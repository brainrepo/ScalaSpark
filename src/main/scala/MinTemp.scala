package com.brainrepo.spark

import org.apache.log4j.{Level, Logger}
import org.apache.spark._
import scala.math.min

object MinTemp {

  def parseLine(line:String) = {
    val fields = line.split(",")
    val stationId = fields(0)
    val entryType = fields(2)
    val temperature = fields(3).toFloat * 0.1f * (9.0f / 5.0f) +32f

    (stationId, entryType, temperature)
  }

  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.ERROR)

    val sc = new SparkContext("local", "Temp")

    val lines = sc.textFile("src/main/resources/1800.csv")

    val rdd = lines.map(parseLine)

    //Filter by tmin
    val minTemps = rdd.filter(x => x._2 == "TMIN")

    //Remove rilevation type (TMIN)
    val statTemps = minTemps.map(x => (x._1, x._3.toFloat))

    val minTempByStation = statTemps.reduceByKey((x,y) => min(x, y))

    val res = minTempByStation.collect()

    res.foreach(println)

    for(result <- res.sorted) {
      val station = result._1
      val temp = result._2
      val formattedTempo = f"$temp%.2f F"
      println(s"$station minimum tem: $formattedTempo ")
    }
  }
}
