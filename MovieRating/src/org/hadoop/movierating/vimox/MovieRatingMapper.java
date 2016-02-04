package org.hadoop.movierating.vimox;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class MovieRatingMapper extends
		Mapper<Object, Text, Text, IntWritable> {

	private static Text movieId = new Text();
	private static IntWritable movieRating = new IntWritable();
	
	public void map(Object key,Text value,Context context)throws IOException,InterruptedException{
		
	
		
		StringTokenizer str = new StringTokenizer(value.toString(),"|");
		if(str.hasMoreTokens()){
			str.nextToken();
		
			String movie1 = str.nextToken();
			System.out.println("key" +movie1);
			movieId.set(movie1);
			
			int rating =Integer.parseInt(str.nextToken());
			System.out.println("value" + rating);
			movieRating.set(rating);
		
		context.write(movieId,movieRating);
		}
		
	}
}
	

