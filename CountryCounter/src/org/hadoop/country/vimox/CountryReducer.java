package org.hadoop.country.vimox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class CountryReducer extends Reducer<Text, IntWritable,Text, IntWritable> {

private static IntWritable result = new IntWritable();
private static String COUNTERGROUP = "debuggroup";
private static String DEBUG1 = "debug1";
	
	public void reduce(Text key,Iterable<IntWritable> values,Context context) throws IOException,InterruptedException{
		
		int sum=0;
		int count=0;
		
		String token = key.toString();
		if(!token.isEmpty()){
			count++;
		System.out.println(context.getCounter(COUNTERGROUP, DEBUG1));
		}
		System.out.println(count);
		for(IntWritable value:values){
			sum+=value.get();
			result.set(sum);
			
		}
		
		context.write(key, result);
	}
	

}

