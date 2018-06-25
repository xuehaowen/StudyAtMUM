package org.labs.extra;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputCommitter;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class stationIdAndTempOrder extends Configured implements Tool {
	public static class Map extends
			Mapper<LongWritable, Text, StringIntPair, IntWritable> {

		private StringIntPair keyPair;

		@Override
		public void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {
			String line = value.toString();
			String stationId = line.substring(4, 10) + "-"
					+ line.substring(10, 15);
			int year;
			int temp;
			try {
				year = Integer.valueOf(line.substring(15, 19));
				temp = Integer.valueOf(line.substring(87, 92));
				keyPair = new StringIntPair(stationId, temp);
				context.write(keyPair, new IntWritable(year));
			} catch (NumberFormatException e) {

			}
		}


	}

	public static class Reduce extends
			Reducer<StringIntPair, IntWritable, StringIntPair, IntWritable> {

		@Override
		public void reduce(StringIntPair key, Iterable<IntWritable> values,
				Context context) throws IOException, InterruptedException

		{
			for(IntWritable value : values){
				context.write(key, value);
			}
		}

	}

	public static class MyTextOutputFormat<StringIntPair, IntWritable> extends
			TextOutputFormat<StringIntPair, IntWritable> {
		@Override
		public Path getDefaultWorkFile(TaskAttemptContext context,
				String extension) throws IOException {
			FileOutputCommitter committer = (FileOutputCommitter) getOutputCommitter(context);
			return new Path(committer.getWorkPath(), "StationTempRecord");

		}
	}

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();

		int res = ToolRunner.run(conf, new stationIdAndTempOrder(), args);

		System.exit(res);
	}

	@Override
	public int run(String[] args) throws Exception {
		Path output = new Path(args[1]);
		output.getFileSystem(getConf()).delete(output, true);

		Job job = Job.getInstance(getConf(), "avgTemp");
		job.setJarByClass(stationIdAndTempOrder.class);

		job.setOutputKeyClass(StringIntPair.class);
		job.setOutputValueClass(IntWritable.class);

		job.setMapperClass(Map.class);
		job.setReducerClass(Reduce.class);

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(MyTextOutputFormat.class);

		// int numReduceTask = 1;
		// if (args.length > 2) {
		// try {
		// int num = Integer.valueOf(args[2]);
		// if (num > 1)
		// numReduceTask = num;
		// } catch (NumberFormatException e) {
		//
		// }
		// }
		// job.setNumReduceTasks(numReduceTask);

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		return job.waitForCompletion(true) ? 0 : 1;
	}
}
