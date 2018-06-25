package org.labs.lab3;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class IntWritableDesc implements WritableComparable<IntWritableDesc> {

	private int value;

	public IntWritableDesc() {
		// TODO Auto-generated constructor stub
	}

	public IntWritableDesc(int value) {
		super();
		this.value = value;
	}

	/** Set the value of this IntWritable. */
	public void set(int value) {
		this.value = value;
	}

	/** Return the value of this IntWritable. */
	public int get() {
		return value;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		value = in.readInt();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeInt(value);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		IntWritableDesc other = (IntWritableDesc) obj;
		if (value != other.value)
			return false;
		return true;
	}

	@Override
	public int compareTo(IntWritableDesc o) {
		int thisValue = this.value;
		int thatValue = o.value;
		return (thisValue < thatValue ? 1 : (thisValue == thatValue ? 0 : -1));
	}

	@Override
	public String toString() {
		return Integer.toString(value);
	}

}
