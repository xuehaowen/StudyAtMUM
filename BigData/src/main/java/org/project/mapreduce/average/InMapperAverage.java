package org.project.mapreduce.average;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

public class InMapperAverage {
	public static class Map extends Mapper<LongWritable, Text, Text, IntPair> {

		private HashMap<String, int[]> map;

		@Override
		protected void setup(Mapper<LongWritable, Text, Text, IntPair>.Context context)
				throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			map = new HashMap<>();
			super.setup(context);
		}

		@Override
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			String line = value.toString();
			String[] info = line.split(" ");
			int quantity;
			try {
				quantity = Integer.valueOf(info[info.length - 1]);
			} catch (NumberFormatException e) {
				quantity = 0;
			}
			if (map.containsKey(info[0])) {
				map.get(info[0])[0] += 1;
				map.get(info[0])[1] += quantity;
			} else {
				map.put(info[0], new int[] { 1, quantity });
			}
		}

		@Override
		protected void cleanup(Mapper<LongWritable, Text, Text, IntPair>.Context context)
				throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			for (String key : map.keySet()) {
				context.write(new Text(key),
						new IntPair(new IntWritable(map.get(key)[0]), new IntWritable(map.get(key)[1])));
			}
			super.cleanup(context);
		}

	}

	public static class Reduce extends Reducer<Text, IntPair, Text, IntPair> {

		@Override
		public void reduce(Text key, Iterable<IntPair> values, Context context) throws IOException, InterruptedException

		{
			int num = 0;
			int sum = 0;
			for (IntPair val : values) {
				num += val.getKey().get();
				sum += val.getValue().get();

			}
			context.write(key, new IntPair(new IntWritable(num), new IntWritable(sum / num)));

		}
	}

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();

		Job job = new Job(conf, "InMapperAverage");
		job.setJarByClass(InMapperAverage.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntPair.class);

		job.setMapperClass(Map.class);
		job.setReducerClass(Reduce.class);

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		FileInputFormat.addInputPath(job, new Path(args[1]));
		FileOutputFormat.setOutputPath(job, new Path(args[2]));

		job.waitForCompletion(true);

	}
}
