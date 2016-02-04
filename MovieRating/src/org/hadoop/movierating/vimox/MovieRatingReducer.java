package org.hadoop.movierating.vimox;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class MovieRatingReducer
		extends
		Reducer<Text, IntWritable, Text, DoubleWritable> {
	
	private	Map<String , Integer> movie = new HashMap<String, Integer>();
		
		public void reduce(Text key,Iterable<IntWritable> values,Context context)throws IOException,InterruptedException{
		
		double sum=0;
		double len=0;
		for(IntWritable value : values){
			sum+=value.get();
			len++;
		}
		double avgRating= sum/len;
		context.write(key, new DoubleWritable(avgRating));
			
		/*	double sum=0;
			for(IntWritable value : values){
				sum+= value.get();
				
			}
			context.write(key, new DoubleWritable(sum));*/

		}
}
