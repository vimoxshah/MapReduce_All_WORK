package org.hadoop.avg.vimox;



import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AverageMarksReducer extends
		Reducer<Text, DoubleWritable, Text, DoubleWritable> {

	private static DoubleWritable result = new DoubleWritable();
	
	public void reduce(Text key, Iterable<DoubleWritable> values, Context context) 
			throws IOException, InterruptedException {
		
		long sum = 0;
		long len = 0;
		for (DoubleWritable value : values) {
			sum+=(long)(value.get());
			len++;
		}
		double t_result = (int) (sum / (double)len);
		result.set(t_result);
		context.write(key, result);
	}
}

