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

public class avgTemp extends Configured implements Tool {
	public static class Map extends
			Mapper<LongWritable, Text, StringIntPair, IntPair> {

		private HashMap<String, HashMap<Integer, int[]>> map;
		private StringIntPair keyPair;
		private IntPair valuePair;

		@Override
		protected void setup(
				Mapper<LongWritable, Text, StringIntPair, IntPair>.Context context)
				throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			map = new HashMap<>();
			super.setup(context);
		}

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
				if (map.containsKey(stationId)) {
					if (map.get(stationId).containsKey(year)) {
						map.get(stationId).get(year)[0] += temp;
						map.get(stationId).get(year)[1] += 1;
					} else {
						int[] pair = new int[] { temp, 1 };
						map.get(stationId).put(year, pair);
					}
				} else {
					HashMap<Integer, int[]> subMap = new HashMap<>();
					int[] pair = new int[] { temp, 1 };
					subMap.put(year, pair);
					map.put(stationId, subMap);
				}
			} catch (NumberFormatException e) {

			}
		}

		@Override
		protected void cleanup(
				Mapper<LongWritable, Text, StringIntPair, IntPair>.Context context)
				throws IOException, InterruptedException {
			for (String key : map.keySet()) {
				for (Integer year : map.get(key).keySet()) {
					int sum = map.get(key).get(year)[0];
					int count = map.get(key).get(year)[1];
					keyPair = new StringIntPair(key, year);
					valuePair = new IntPair(sum, count);
					context.write(keyPair, valuePair);
				}
			}
			super.cleanup(context);
		}

	}

	public static class Reduce extends
			Reducer<StringIntPair, IntPair, StringIntPair, IntWritable> {
		private String prev = null;
		private List<IntPair> list = new ArrayList<>();

		@Override
		public void reduce(StringIntPair key, Iterable<IntPair> values,
				Context context) throws IOException, InterruptedException

		{
			if (prev != null && !key.getKey().equals(prev)) {
				Collections.sort(list);
				for (IntPair pair : list) {
					StringIntPair output = new StringIntPair(prev,
							pair.getKey());
					context.write(output, new IntWritable(pair.getValue()));
				}
				list.clear();
			}
			int sum = 0;
			int count = 0;
			for (IntPair val : values) {
				sum += val.getKey();
				count += val.getValue();

			}
			IntPair pair = new IntPair(sum / count, key.getValue());
			list.add(pair);
			prev = key.getKey();
		}

		@Override
		protected void cleanup(
				Reducer<StringIntPair, IntPair, StringIntPair, IntWritable>.Context context)
				throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			Collections.sort(list);
			for (IntPair pair : list) {
				StringIntPair output = new StringIntPair(prev, pair.getKey());
				context.write(output, new IntWritable(pair.getValue()));
			}
			super.cleanup(context);
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

		int res = ToolRunner.run(conf, new avgTemp(), args);

		System.exit(res);
	}

	@Override
	public int run(String[] args) throws Exception {
		Path output = new Path(args[1]);
		output.getFileSystem(getConf()).delete(output, true);

		Job job = Job.getInstance(getConf(), "avgTemp");
		job.setJarByClass(avgTemp.class);

		job.setOutputValueClass(IntWritable.class);
		job.setMapOutputKeyClass(StringIntPair.class);
		job.setMapOutputValueClass(IntPair.class);

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
