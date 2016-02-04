package org.hadoop.avg.vimox;


import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class AverageMarksMapper 
		extends Mapper<Object, Text, Text, DoubleWritable> {
	
	private static Text student = new Text(); 
	private static DoubleWritable marks = new DoubleWritable();
	
	public void map(Object key, Text value, Context context) 
			throws IOException, InterruptedException { 
		
		StringTokenizer st = new StringTokenizer(value.toString());
		if (st.countTokens()==2) {
			student.set(st.nextToken());
			marks.set(Double.parseDouble(st.nextToken()));
			context.write(student, marks);
		}
	}
}
