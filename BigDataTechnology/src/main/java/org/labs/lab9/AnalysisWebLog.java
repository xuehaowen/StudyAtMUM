package org.labs.lab9;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

public class AnalysisWebLog {

	public static void main(String[] args) throws Exception {
		// Create a Java Spark Context
		JavaSparkContext sc = new JavaSparkContext(new SparkConf().setAppName(
				"analysis").setMaster("local"));

		// Load our input data
		JavaRDD<String> lines = sc.textFile(args[0]);
		// lines.foreach(System.out::println);

		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MMM/yyyy");
		String start = args[2];
		String end = args[3];
		Date startDate = sdf1.parse(start);
		Date endDate = sdf1.parse(end);

		String logEntryPattern = "^([\\d.]+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(.+?)\" (\\d{3}) (\\d+)";

		JavaRDD<Matcher> matchers = lines.map(
				line -> Pattern.compile(logEntryPattern).matcher(line)).cache();
		// long count1 = matchers.filter(m -> m.matches() && m.groupCount() ==
		// 7)
		// .count();
		long count = matchers
				.filter(m -> (m.matches() && m.groupCount() == 7)
						&& (sdf2.parse(m.group(4)).compareTo(startDate) > -1
								&& sdf2.parse(m.group(4)).compareTo(endDate) < 1 && m
								.group(6).equals("404"))).count();
		String countString = "404 error has " + count + " times";
		JavaRDD<String> countRdd = sc.parallelize(Arrays.asList(countString));
		JavaRDD<String> addressRdd = matchers
				.filter(m -> m.matches() && m.groupCount() == 7)
				.mapToPair(m -> new Tuple2<String, Integer>(m.group(1), 1))
				.reduceByKey((x, y) -> x + y).filter(t -> t._2 > 20)
				.map(t -> t._1);

		JavaRDD<String> result = sc.union(countRdd, addressRdd);
		// Save the word count back out to a text file, causing evaluation
		result.saveAsTextFile(args[1]);
		sc.close();
	}

}
