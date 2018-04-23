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

import java.io.IOException;

public class StripesFrequency {
	public static class Map extends Mapper<LongWritable, Text, Text, MyMapWritable> {

		private final static FloatWritable one = new FloatWritable(1);

		@Override
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			String line = value.toString();
			String[] info = line.split(" ");
			for (int i = 0; i < info.length - 1; i++) {
				MyMapWritable map = new MyMapWritable();
				Text t1 = new Text(info[i]);
				for (int j = i + 1; j < info.length; j++) {
					if (info[i].equals(info[j]))
						break;
					Text t2 = new Text(info[j]);
					if (map.containsKey(t2)) {
						float num = Float.valueOf(map.get(t2).toString());
						map.put(t2, new FloatWritable(num + 1));
					} else {
						map.put(t2, one);
					}
				}
				context.write(t1, map);

			}

		}

	}

	public static class Reduce extends Reducer<Text, MyMapWritable, Text, MyMapWritable> {

		@Override
		public void reduce(Text key, Iterable<MyMapWritable> values, Context context)
				throws IOException, InterruptedException

		{
			MyMapWritable map = new MyMapWritable();
			float num = 0;
			for(MyMapWritable val : values) {
				for(Writable w : val.keySet()) {
					float num1 = Float.valueOf(val.get(w).toString());
					num += num1;
					if(map.containsKey(w)) {
						float num2 = Float.valueOf(map.get(w).toString());
						map.put(w, new FloatWritable(num1 + num2));
					}else {
						map.put(w, val.get(w));
					}
				}
			}
			for(Writable w : map.keySet()) {
				float sum = Float.valueOf(map.get(w).toString());
				map.put(w, new FloatWritable(sum / num));
			}
			context.write(key, map);
		}
	}

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();

		Job job = new Job(conf, "PairsFrequency");
		job.setJarByClass(StripesFrequency.class);

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
