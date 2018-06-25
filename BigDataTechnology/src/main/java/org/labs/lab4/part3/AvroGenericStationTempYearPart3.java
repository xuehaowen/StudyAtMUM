package org.labs.lab4.part3;

import java.io.File;
import java.io.IOException;

import org.apache.avro.Schema;
import org.apache.avro.Schema.Type;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.mapred.AvroKey;
import org.apache.avro.mapred.AvroValue;
import org.apache.avro.mapreduce.AvroJob;
import org.apache.avro.mapreduce.AvroKeyValueOutputFormat;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class AvroGenericStationTempYearPart3 extends Configured implements Tool {

	private static Schema SCHEMA;

	public static class AvroMapper
			extends
			Mapper<LongWritable, Text, AvroKey<GenericRecord>, AvroValue<String>> {
		private NcdcLineReaderUtilsPart3 utils = new NcdcLineReaderUtilsPart3();
		private GenericRecord record = new GenericData.Record(SCHEMA);

		@Override
		protected void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {
			utils.parse(value.toString());

			if (utils.isValidTemperature()) {
				// record.put("stationId", utils.getStationId());
				record.put("temperature", utils.getAirTemperature());
				record.put("year", utils.getYearInt());
				// System.out.println(utils.getStationId());
				context.write(new AvroKey<GenericRecord>(record),
						new AvroValue<String>(utils.getStationId()));
			}
		}
	}

	public static class AvroReducer
			extends
			Reducer<AvroKey<GenericRecord>, AvroValue<String>, AvroKey<GenericRecord>, AvroValue<String>> {
		private int year = 0;

		@Override
		protected void reduce(AvroKey<GenericRecord> key,
				Iterable<AvroValue<String>> values, Context context)
				throws IOException, InterruptedException {
			// Complete the reducer logic.
			// System.out.println(key.datum().get("year"));
			int yearNow = (Integer) key.datum().get("year");
			if (year != yearNow)
				for (AvroValue<String> stationId : values) {
					 System.out.println("stationId:"+stationId.datum());
					context.write(key, stationId);
				}
			year = yearNow;
		}
	}

	@Override
	public int run(String[] args) throws Exception {
		if (args.length != 3) {
			System.err
					.printf("Usage: %s [generic options] <input> <output> <schema-file>\n",
							getClass().getSimpleName());
			ToolRunner.printGenericCommandUsage(System.err);
			return -1;
		}

		Path output = new Path(args[1]);
		output.getFileSystem(getConf()).delete(output, true);

		Job job = Job.getInstance();
		job.setJarByClass(AvroGenericStationTempYearPart3.class);
		job.setJobName("Avro Station-Temp-Year");

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		String schemaFile = args[2];

		SCHEMA = new Schema.Parser().parse(new File(schemaFile));

		job.setMapperClass(AvroMapper.class);
		job.setReducerClass(AvroReducer.class);

		// Define the map output key schema

		AvroJob.setMapOutputKeySchema(job, SCHEMA);
		AvroJob.setMapOutputValueSchema(job, Schema.create(Type.STRING));
		AvroJob.setOutputKeySchema(job, SCHEMA);
		AvroJob.setOutputValueSchema(job, Schema.create(Type.STRING));

		job.setOutputFormatClass(AvroKeyValueOutputFormat.class); // Uncomment this
		// line

		return job.waitForCompletion(true) ? 0 : 1;
	}

	public static void main(String[] args) throws Exception {
		int exitCode = ToolRunner.run(new AvroGenericStationTempYearPart3(), args);
		System.exit(exitCode);
	}
}