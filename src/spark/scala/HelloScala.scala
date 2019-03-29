import org.apache.spark.{SparkConf, SparkContext}


object HelloScala {
  def main(args: Array[String]) {

    def cleanText(s: String): String = {
      s.replaceAll("""[\p{Punct}]""", "")
        .replaceAll("""\b\p{IsLetter}{1,2}\b""", "")
        .toLowerCase
    }

    //Create a SparkContext to initialize Spark
    val conf = new SparkConf()
    conf.setMaster("local")
    conf.setAppName("Word Count")
    val sc = new SparkContext(conf)

    // Load the text into a Spark RDD, which is a distributed representation of each line of text
    val textFile = sc.textFile("src/main/resources/deledda_canne_al_vento.txt")

    //word count
    val counts = textFile
      .flatMap(line => line.split(" "))
      .map(word => (cleanText(word), 1))
      .reduceByKey(_ + _)
      .sortBy(_._2, ascending = false)

    counts.foreach(println)
    System.out.println("Total words: " + counts.count());
    counts.saveAsTextFile("src/main/resources/deledda_count.txt")


  }
}
