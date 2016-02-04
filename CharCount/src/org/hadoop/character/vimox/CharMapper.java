package org.hadoop.character.vimox;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CharMapper extends Mapper<Object, Text, Text, IntWritable> {
	
	private static final IntWritable one = new IntWritable(1);
	private static Text word = new Text();
	
	public void map(Object key , Text value, Context context) throws IOException,InterruptedException{
		
		StringTokenizer str = new StringTokenizer(value.toString());
		
		while(str.hasMoreTokens()){
			word.set(str.nextToken());
			
			for(int i=0;i<word.toString().length(); i++){
				String singleChar = word.toString().substring(i, i+1);
				Text ch = new Text(singleChar);
				context.write(ch, one);
			}
		}
		
	}
}
