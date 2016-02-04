package org.hadoop.counter.vimox;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CounterMapper extends Mapper<Object, Text, Text, IntWritable> {
	
	private static final IntWritable ONE = new IntWritable(1);
	private static Text word = new Text();
	
	public void map(Object key,Text value,Context context) throws IOException,InterruptedException{
		
		StringTokenizer str = new StringTokenizer(value.toString());
		while(str.hasMoreTokens()){
			
			word.set(str.nextToken());
			context.write(word, ONE);
			
		}
	}

}
