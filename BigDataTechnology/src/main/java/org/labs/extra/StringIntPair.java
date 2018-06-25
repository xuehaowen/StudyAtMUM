package org.labs.extra;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class StringIntPair implements WritableComparable<StringIntPair> {
	private Text key;
	private int value;

	public StringIntPair() {
		key = new Text();
	}

	public StringIntPair(String key, int value) {
		this.key = new Text(key);
		this.value = value;
	}

	public String getKey() {
		return key.toString();
	}

	public void setKey(String key) {
		this.key.set(key);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return key + " " + value;
	}

	@Override
	public int compareTo(StringIntPair o) {
		int k = key.compareTo(o.key);
		if (k == 0)
			return Integer.compare(o.getValue(), value);
		return k;

	}

	@Override
	public void write(DataOutput dataOutput) throws IOException {
		key.write(dataOutput);
		dataOutput.writeInt(value);

	}

	@Override
	public void readFields(DataInput dataInput) throws IOException {
		key.readFields(dataInput);
		value = dataInput.readInt();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + value;
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
		StringIntPair other = (StringIntPair) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (value != other.value)
			return false;
		return true;
	}

}
