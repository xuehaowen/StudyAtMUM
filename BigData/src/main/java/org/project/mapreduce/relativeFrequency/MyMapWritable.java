package org.project.mapreduce.relativeFrequency;


import org.apache.hadoop.io.MapWritable;

public class MyMapWritable extends MapWritable {

	@Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (Object key : this.keySet()) {
            result.append("{" + key.toString() + " = " + this.get(key) + "}");
        }
        return result.toString();
    }

}
