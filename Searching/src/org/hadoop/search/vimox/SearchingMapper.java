package org.hadoop.search.vimox;
import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class SearchingMapper extends Mapper<Object, Text, Text, IntWritable> {
	
	private static final IntWritable one = new IntWritable(1);
	private static Text word = new Text();
	
	public void map(Object key,Text value,Context context) throws IOException,InterruptedException{
		
		Configuration conf = context.getConfiguration();
		String search = conf.get("Searching");
		
		StringTokenizer str = new StringTokenizer(value.toString());
		while(str.hasMoreTokens()){
			word.set(str.nextToken());
			
			if(word.toString().equals(search)){
				context.write(word, one);
			}
		}
	}
			

}
