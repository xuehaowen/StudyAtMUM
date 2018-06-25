package org.labs.lab3;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
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
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.labs.extra.IntPair;

public class WithCombinerAvg extends Configured implements Tool {

	public static class Map extends Mapper<LongWritable, Text, IntWritable, IntPair> {

		@Override
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			String line = value.toString();
			int year;
			int temp;
			try {
				year = Integer.valueOf(line.substring(15, 19));
				temp = Integer.valueOf(line.substring(87, 92));
				context.write(new IntWritable(year), new IntPair(temp, 1));
			} catch (NumberFormatException e) {

			}
		}

	}

	public static class Combiner extends Reducer<IntWritable, IntPair, IntWritable, IntPair> {

		@Override
		public void reduce(IntWritable key, Iterable<IntPair> values, Context context)
				throws IOException, InterruptedException

		{
			int sum = 0;
			int count = 0;
			for (IntPair val : values) {
				sum += val.getKey();
				count += val.getValue();

			}
			context.write(key, new IntPair(sum, count));

		}
	}

	public static class Reduce extends Reducer<IntWritable, IntPair, IntWritable, IntWritable> {

		@Override
		public void reduce(IntWritable key, Iterable<IntPair> values, Context context)
				throws IOException, InterruptedException

		{
			int sum = 0;
			int count = 0;
			for (IntPair val : values) {
				sum += val.getKey();
				count += val.getValue();

			}
			context.write(key, new IntWritable(sum / count));

		}
	}

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();

		int res = ToolRunner.run(conf, new WithCombinerAvg(), args);

		System.exit(res);

	}

	@Override
	public int run(String[] args) throws Exception {
		Path output = new Path(args[1]);
		output.getFileSystem(getConf()).delete(output, true);

		Job job = Job.getInstance(getConf(), "WithCombinerAvg");
		job.setJarByClass(WithCombinerAvg.class);

		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(IntWritable.class);
		job.setMapOutputValueClass(IntPair.class);

		job.setMapperClass(Map.class);
		job.setReducerClass(Reduce.class);
		job.setCombinerClass(Combiner.class);

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		int numReduceTask = 1;
		if (args.length > 2) {
			try {
				int num = Integer.valueOf(args[2]);
				if (num > 1)
					numReduceTask = num;
			} catch (NumberFormatException e) {

			}
		}
		job.setNumReduceTasks(numReduceTask);

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		return job.waitForCompletion(true) ? 0 : 1;
	}

}
