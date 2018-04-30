package org.project.spark
import java.io._
import sys.process._
import java.net.URL
import scala.io.Source
import scala.math.random
import org.apache.spark._
import org.apache.spark.sql.{ DataFrame, SQLContext }
import au.com.bytecode.opencsv.CSVParser
import org.apache.spark.sql.functions._

object ScalaExample extends App {
  case class BRY(breaks: Int, wool: String, tension: String)

  override def main(args: Array[String]) {
    //    System.setProperty("hadoop.home.dir", "c://winutil//")
    val conf = new SparkConf().setAppName("ScalaExample").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    import sqlContext.implicits._
    var csv = sc.textFile("warpbreaks.csv")
    var dataWithHeader = csv.map(rec => rec.split(","))
    var header = dataWithHeader.first()
    var data = dataWithHeader.filter(_(0) != header(0))
    val population = data.map(d => BRY(d(1).toInt, d(2), d(3))).toDF()
    //    var avgData = population.groupBy("tension").agg(avg("breaks"), variance("breaks"))
    var sample = population.sample(false, 0.25)
    
    val sum = (1 to 10)
      .map(x => sample.sample(true, 1).groupBy("tension").agg(avg("breaks"), variance("breaks")).rdd)
      .reduce(_ union _).filter(row => !row(2).toString().equals("NaN"))
      .map(row => (row(0).toString(), row(1).toString().toDouble, row(2).toString().toDouble)).toDF()
    sum.groupBy("_1").agg(mean("_2").alias("mean"), mean("_3").alias("variance")).withColumnRenamed("_1", "tension").show()
  }

}