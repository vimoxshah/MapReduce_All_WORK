package org.hadoop.partition.vimox;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class PartitionMapper extends Mapper<Object, Text, Text, IntWritable> {

	private static final Text word = new Text();
	private static IntWritable ONE = new IntWritable(1);
	
	public void map(Object key,Text value,Context context) throws IOException,InterruptedException{
		
		StringTokenizer str =new StringTokenizer(value.toString());
		while(str.hasMoreTokens()){
			word.set(str.nextToken());
			context.write(word, ONE);
		}
	}
}
