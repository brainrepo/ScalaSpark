package com.brainrepo.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._


object FriedByAge {

  def parseLine(line:String) = {
    val friends = line.split(",")
    val age = friends(2).toInt
    val numFriends = friends(3).toInt

    (age, numFriends)
  }

  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.ERROR)

    val sc = new SparkContext("local", "Rating Count")

    val lines = sc.textFile("src/main/resources/fakefriends.csv")

    val rdd = lines.map(parseLine)

    //mappa i valori in una tupla con come primo valore il valore iniziale dell'elemento e come secondo un 1
    //che sarà un cantatore per dividere i valori incrementati per calcolare la media
    val totalByAge = rdd.mapValues(x => (x, 1))
      //riduce per chiave sommando sia il primo valore che è il numero degli amici che il secondo che è il numero degli
      //utenti che hanno quegli amici
      //vedi https://backtobazics.com/big-data/spark/apache-spark-reducebykey-example/
      .reduceByKey((acc,value) => (acc._1 + value._1, acc._2 + value._2))

    //Divido il numero degli amici per le persone che hanno quegli amici
    val averageByAge = totalByAge.mapValues(x => x._1 / x._2 )

    //applico la trasformazione
    val results = averageByAge.collect()

    results.sorted.foreach(println(_))

  }
}
