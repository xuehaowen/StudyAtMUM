package org.labs.lab2;
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

public class WordCountForQc extends Configured implements Tool {

	public static class WordCountMapper extends
			Mapper<LongWritable, Text, Text, IntWritable> {

		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();
		private String hadoop = "hadoop";
		private String java = "java";

		@Override
		public void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {
			for (String token : value.toString().split("\\s+")) {
				if(token.toLowerCase().equals(hadoop) || token.toLowerCase().equals(java)){
					word.set(token.toLowerCase());
					context.write(word, one);
				}
				
			}
		}
	}

	public static class WordCountReducer extends
			Reducer<Text, IntWritable, Text, IntWritable> {
		private IntWritable result = new IntWritable();

		@Override
		public void reduce(Text key, Iterable<IntWritable> values,
				Context context) throws IOException, InterruptedException {
			int sum = 0;
			for (IntWritable val : values) {
				sum += val.get();
			}
			result.set(sum);
			context.write(key, result);
		}
	}

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();

		int res = ToolRunner.run(conf, new WordCountForQc(), args);

		System.exit(res);
	}

	@Override
	public int run(String[] args) throws Exception {
		Path output = new Path(args[1]);
		output.getFileSystem(getConf()).delete(output, true);

		Job job = new Job(getConf(), "WordCount");
		job.setJarByClass(WordCountForQc.class);

		job.setMapperClass(WordCountMapper.class);
		job.setReducerClass(WordCountReducer.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

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
