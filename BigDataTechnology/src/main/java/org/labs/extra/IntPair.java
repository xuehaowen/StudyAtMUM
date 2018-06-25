package org.labs.extra;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.WritableComparable;

public class IntPair implements WritableComparable<IntPair> {

	private int key;
	private int value;

	public IntPair() {

	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public IntPair(int key, int value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public void write(DataOutput dataOutput) throws IOException {
		dataOutput.writeInt(key);
		dataOutput.writeInt(value);
	}

	@Override
	public void readFields(DataInput dataInput) throws IOException {
		key = dataInput.readInt();
		value = dataInput.readInt();
	}

	@Override
	public int compareTo(IntPair o) {
		int k = Integer.compare(getKey(), o.getKey());
		if (k == 0)
			return Integer.compare(value, o.getValue());
		return -k;
	}

	@Override
	public String toString() {
		return "(" + key + "," + value + ")";
	}

}
