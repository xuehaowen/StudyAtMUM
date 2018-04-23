package org.project.mapreduce.average;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.WritableComparable;

public class IntPair implements WritableComparable<IntPair> {

	private IntWritable key;
	private IntWritable value;

	public IntPair() {
		key = new IntWritable();
		value = new IntWritable();
	}

	public IntWritable getKey() {
		return key;
	}

	public void setKey(IntWritable key) {
		this.key = key;
	}

	public IntWritable getValue() {
		return value;
	}

	public void setValue(IntWritable value) {
		this.value = value;
	}

	public IntPair(IntWritable key, IntWritable value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public void write(DataOutput dataOutput) throws IOException {
		key.write(dataOutput);
		value.write(dataOutput);
	}

	@Override
	public void readFields(DataInput dataInput) throws IOException {
		key.readFields(dataInput);
		value.readFields(dataInput);
	}

	@Override
	public int compareTo(IntPair o) {
		if (getKey().equals(o.getKey())) {
			return getValue().compareTo(o.getValue());
		} else {
			return this.getKey().compareTo(o.getKey());
		}
	}

	@Override
	public String toString() {
		return "(" + key + "," + value + ")";
	}

}
