package org.hadoop.country.vimox;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class CountryMapper extends Mapper<Object, Text, Text,IntWritable> {
	

	private static final IntWritable one = new IntWritable(1);
	private static Text word = new Text();

	public void map(Object key,Text value,Context context) throws IOException,InterruptedException{
		
		
		StringTokenizer str = new StringTokenizer(value.toString(),",");
		if(str.hasMoreTokens()){
			
			for(int i=0;i<=1;i++){
				String value1 = str.nextToken();
				//System.out.println(value1);
				word.set(value1);
			}
			
			context.write(word, one);
			
		}
	}
			

}

