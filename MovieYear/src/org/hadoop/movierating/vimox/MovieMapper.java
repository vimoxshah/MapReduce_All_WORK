package org.hadoop.movierating.vimox;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MovieMapper extends Mapper<Object, Text, Text, IntWritable> {
	
	private static Text movie = new Text();
	private static IntWritable year = new IntWritable();
	
	public void map(Object key,Text value,Context context)throws IOException,InterruptedException{
		
	
		
		StringTokenizer str = new StringTokenizer(value.toString(),"|");
			
		while(str.hasMoreTokens()){
			str.nextToken();
		
			String movie1 = str.nextToken().toString();
			System.out.println("key" +movie1);
			movie.set(movie1);
			
			int yr =Integer.parseInt(str.nextToken());
			System.out.println("value" + yr);
			year.set(yr);
		
		context.write(movie,year);
		}
		
	}

}
