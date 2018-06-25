package org.labs.lab3;

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
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.labs.extra.IntPair;

import java.io.IOException;
import java.util.HashMap;

public class PartitionerInMapperAvgDesc extends Configured implements Tool {
	public static class Map extends Mapper<LongWritable, Text, IntWritableDesc, IntPair> {

		private HashMap<Integer, int[]> map;

		@Override
		protected void setup(Mapper<LongWritable, Text, IntWritableDesc, IntPair>.Context context)
				throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			map = new HashMap<>();
			super.setup(context);
		}

		@Override
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			String line = value.toString();
			int year;
			int temp;
			try {
				year = Integer.valueOf(line.substring(15, 19));
				temp = Integer.valueOf(line.substring(87, 92));
				if (map.containsKey(year)) {
					map.get(year)[0] += temp;
					map.get(year)[1] += 1;
				} else {
					map.put(year, new int[] { temp, 1 });
				}
			} catch (NumberFormatException e) {

			}
		}

		@Override
		protected void cleanup(Mapper<LongWritable, Text, IntWritableDesc, IntPair>.Context context)
				throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			for (Integer key : map.keySet()) {
				context.write(new IntWritableDesc(key),
						new IntPair(map.get(key)[0], map.get(key)[1]));
			}
			super.cleanup(context);
		}

	}

	public static class Partitioner extends HashPartitioner<IntWritableDesc, IntPair> {
		@Override
		public int getPartition(IntWritableDesc key, IntPair value, int numReduceTasks) {
			if (key.get() < 1930)
				return 0;
			else
				return 1;
		}
	}

	public static class Reduce extends Reducer<IntWritableDesc, IntPair, IntWritableDesc, IntWritable> {

		@Override
		public void reduce(IntWritableDesc key, Iterable<IntPair> values, Context context)
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

		int res = ToolRunner.run(conf, new PartitionerInMapperAvgDesc(), args);

		System.exit(res);
	}

	@Override
	public int run(String[] args) throws Exception {
		Path output = new Path(args[1]);
		output.getFileSystem(getConf()).delete(output, true);

		Job job = Job.getInstance(getConf(), "PartitionerInMapperAvgDesc");
		job.setJarByClass(PartitionerInMapperAvgDesc.class);

		job.setOutputKeyClass(IntWritableDesc.class);
		job.setOutputValueClass(IntWritable.class);
		job.setMapOutputValueClass(IntPair.class);

		job.setMapperClass(Map.class);
		job.setReducerClass(Reduce.class);

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
		job.setPartitionerClass(Partitioner.class);

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		return job.waitForCompletion(true) ? 0 : 1;
	}
}
