package org.project.mapreduce.relativeFrequency;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;

public class HybirdFrequency {
	public static class Map extends Mapper<LongWritable, Text, StringPair, FloatWritable> {

		private HashMap<StringPair, Integer> map;
		private Text t1;
		private Text t2;

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

	public static class Reduce extends Reducer<StringPair, FloatWritable, Text, MyMapWritable> {
		private Text prev;
		private MyMapWritable map;

		@Override
		protected void setup(Reducer<StringPair, FloatWritable, Text, MyMapWritable>.Context context)
				throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			prev = null;
			map = new MyMapWritable();
			super.setup(context);
		}

		@Override
		public void reduce(StringPair key, Iterable<FloatWritable> values, Context context)
				throws IOException, InterruptedException {
			Text keyText = new Text(key.getKey());
			Text keyValue = new Text(key.getValue());
			if (!keyText.equals(prev) && prev != null) {
				float total = 0;
				for (Writable w : map.values()) {
					total += Float.valueOf(w.toString());
				}
				for (Writable w : map.keySet()) {
					float sum = Float.valueOf(map.get(w).toString());
					map.put(w, new FloatWritable(sum / total));
				}
				context.write(prev, map);
				map.clear();

			}
			float sum = 0;
			for (FloatWritable val : values) {
				sum += val.get();
			}

			if (map.containsKey(keyValue)) {
				float value = Float.valueOf(map.get(keyValue).toString());
				map.put(keyValue, new FloatWritable(value + sum));
			} else {
				map.put(keyValue, new FloatWritable(sum));
			}
			prev = keyText;
		}

		@Override
		protected void cleanup(Reducer<StringPair, FloatWritable, Text, MyMapWritable>.Context context)
				throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			float total = 0;
			for (Writable w : map.values()) {
				total += Float.valueOf(w.toString());
			}
			for (Writable w : map.keySet()) {
				float sum = Float.valueOf(map.get(w).toString());
				map.put(w, new FloatWritable(sum / total));
			}
			context.write(prev, map);
			super.cleanup(context);
		}

	}

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();

		Job job = new Job(conf, "HybirdFrequency");
		job.setJarByClass(HybirdFrequency.class);

		job.setMapOutputKeyClass(StringPair.class);
		job.setMapOutputValueClass(FloatWritable.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(MyMapWritable.class);

		job.setMapperClass(Map.class);
		job.setReducerClass(Reduce.class);

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		FileInputFormat.addInputPath(job, new Path(args[1]));
		FileOutputFormat.setOutputPath(job, new Path(args[2]));

		job.waitForCompletion(true);

	}
}
