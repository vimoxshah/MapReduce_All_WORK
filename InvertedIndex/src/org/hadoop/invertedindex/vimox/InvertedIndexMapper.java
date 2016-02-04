package org.hadoop.invertedindex.vimox;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class InvertedIndexMapper extends
		Mapper<Object, Text, Text, Text> {
	


	private static Text word = new Text();
	private static Text filename = new Text();
	
	public void map(Object key,Text value,Context context) throws IOException,InterruptedException{
		
		String filenamestr =  ((FileSplit)context.getInputSplit()).getPath().getName();
		filename = new Text(filenamestr);
		
		StringTokenizer str = new StringTokenizer(value.toString());
		
		while(str.hasMoreTokens()){
			word.set(str.nextToken());
			context.write(filename,word);
		}
				
	}

}
