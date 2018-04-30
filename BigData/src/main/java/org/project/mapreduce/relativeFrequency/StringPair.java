package org.project.mapreduce.relativeFrequency;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class StringPair implements WritableComparable<StringPair> {
	private Text key;
	private Text value;

	public StringPair() {
		key = new Text();
		value = new Text();
	}

	public StringPair(Text key, Text value) {
		this.key = key;
		this.value = value;
	}

	public Text getKey() {
		return key;
	}

	public void setKey(Text key) {
		this.key = key;
	}

	public Text getValue() {
		return value;
	}

	public void setValue(Text value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "(" + key + "," + value + ")";
	}

	@Override
	public int compareTo(StringPair o) {
		if (getKey().equals(o.getKey())) {
			return getValue().compareTo(o.getValue());
		} else {
			return this.getKey().compareTo(o.getKey());
		}

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StringPair other = (StringPair) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
}
