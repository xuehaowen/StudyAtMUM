package org.labs.lab9;

import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

public class Spark2WordCountjdk8
{

	public static void main(String[] args) throws Exception
	{
		// Create a Java Spark Context
		JavaSparkContext sc = new JavaSparkContext(new SparkConf().setAppName("wordCount").setMaster("local"));

		// Load our input data
		JavaRDD<String> lines = sc.textFile(args[0]);

		//
		int threshold = Integer.valueOf(args[2]);
		// Calculate word count
		JavaPairRDD<String, Integer> counts = 
				lines
					.flatMap(line -> Arrays.asList(line.toLowerCase().split("[\\s\\(\\),\\._]+")))
					.filter(w -> !w.isEmpty())
					.mapToPair(w -> new Tuple2<String, Integer>(w, 1))
					.reduceByKey((x, y) -> x + y)
					.filter(t -> t._2.intValue() > threshold)
//					.foreach(System.out::println);
					.flatMap(p -> Arrays.asList(p._1.split("[^a-z]*")))
					.mapToPair(w -> new Tuple2<String, Integer>(w, 1))
					.reduceByKey((x, y) -> x + y);

		// Save the word count back out to a text file, causing evaluation
		counts.saveAsTextFile(args[1]);

		sc.close();
	}
}
