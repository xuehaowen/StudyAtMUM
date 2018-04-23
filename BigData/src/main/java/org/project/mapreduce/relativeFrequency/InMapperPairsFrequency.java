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
import java.util.HashMap;

public class InMapperPairsFrequency {
	public static class Map extends Mapper<LongWritable, Text, StringPair, FloatWritable> {

		private HashMap<StringPair, Integer> map;
		private Text t1;
		private Text t2;
		private final static Text t3 = new Text("*");

		@Override
		protected void setup(Mapper<LongWritable, Text, StringPair, FloatWritable>.Context context)
				throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			map = new HashMap<>();
			super.setup(context);
		}

		@Override
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			String line = value.toString();
			String[] info = line.split(" ");
			for (int i = 0; i < info.length - 1; i++) {
				t1 = new Text(info[i]);
				int num = 0;
				for (int j = i + 1; j < info.length; j++) {

					if (info[i].equals(info[j]))
						break;
					t2 = new Text(info[j]);
					StringPair pair1 = new StringPair(t1, t2);

					if (map.containsKey(pair1)) {
						int sum = map.get(pair1).intValue();
						map.put(pair1, new Integer(sum + 1));
					} else {
						map.put(pair1, 1);
					}
					num++;
				}
				StringPair pair2 = new StringPair(t1, t3);
				if (map.containsKey(pair2)) {
					int pre = map.get(pair2).intValue();
					map.put(pair2, new Integer(pre + num));
				} else {
					map.put(pair2, num);
				}

			}

		}

		@Override
		protected void cleanup(Mapper<LongWritable, Text, StringPair, FloatWritable>.Context context)
				throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			for (StringPair pair : map.keySet()) {
				context.write(pair, new FloatWritable(map.get(pair)));
			}
			super.cleanup(context);
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

		Job job = new Job(conf, "InMapperPairsFrequency");
		job.setJarByClass(InMapperPairsFrequency.class);

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
