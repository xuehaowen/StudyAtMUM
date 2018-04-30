package org.project.mapreduce.relativeFrequency;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
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
import java.util.ArrayList;
import java.util.List;

public class PairsFrequency {
	public static class Map extends Mapper<LongWritable, Text, StringPair, FloatWritable> {

		private final static FloatWritable one = new FloatWritable(1);

		@Override
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			String line = value.toString();
			String[] info = line.split(" ");
			for (int i = 0; i < info.length - 1; i++) {
				List<String> N = new ArrayList<>();
				for (int j = i + 1; j < info.length; j++) {
					if (info[i].equals(info[j]))
						break;
					N.add(info[j]);
				}
				for (String n : N) {
					context.write(new StringPair(new Text(info[i]), new Text("*")), one);
					context.write(new StringPair(new Text(info[i]), new Text(n)), one);
				}

			}

		}

	}

	public static class Reduce extends Reducer<StringPair, FloatWritable, StringPair, FloatWritable> {

		private float num;
		private Text text;

		@Override
		protected void setup(Reducer<StringPair, FloatWritable, StringPair, FloatWritable>.Context context)
				throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			text = new Text("*");
			num = 0;
			super.setup(context);
		}

		@Override
		public void reduce(StringPair key, Iterable<FloatWritable> values, Context context)
				throws IOException, InterruptedException

		{
			float sum = 0;
			for (FloatWritable val : values) {
				sum += val.get();
			}
			if (text.equals(key.getValue())) {
				num = sum;
			} else {
				context.write(key, new FloatWritable(sum / num));
			}

		}
	}

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();

		Job job = new Job(conf, "PairsFrequency");
		job.setJarByClass(PairsFrequency.class);

		job.setOutputKeyClass(StringPair.class);
		job.setOutputValueClass(FloatWritable.class);

		job.setMapperClass(Map.class);
		job.setReducerClass(Reduce.class);

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		FileInputFormat.addInputPath(job, new Path(args[1]));
		FileOutputFormat.setOutputPath(job, new Path(args[2]));

		job.waitForCompletion(true);

	}
}
