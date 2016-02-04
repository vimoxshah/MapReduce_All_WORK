package org.hadoop.counter.vimox;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CounterReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	
	static enum Vowels { BEGINS_WITH_A, BEGINS_WITH_E, BEGINS_WITH_I, BEGINS_WITH_O, BEGINS_WITH_U, ALL };
	private static IntWritable  result = new IntWritable();
	
	public void reduce( Text key,Iterable<IntWritable> values,Context context)throws IOException,InterruptedException{
		
		int sum =0;
		String token = key.toString();
		
		if(token.substring(0, 1).equals("a") || token.substring(0, 1).equals("A")){
			context.getCounter(Vowels.BEGINS_WITH_A).increment(1);
		}else if(token.substring(0, 1).equals("e") || token.substring(0, 1).equals("E")){
			context.getCounter(Vowels.BEGINS_WITH_E).increment(1);
		}else if(token.substring(0, 1).equals("i") || token.substring(0, 1).equals("I")){
			context.getCounter(Vowels.BEGINS_WITH_I).increment(1);
		}else if(token.substring(0, 1).equals("o") || token.substring(0, 1).equals("O")){
			context.getCounter(Vowels.BEGINS_WITH_O).increment(1);
		}else if(token.substring(0,1).equals("u") || token.substring(0,1).equals("U")){
			context.getCounter(Vowels.BEGINS_WITH_U).increment(1);
		}
		context.getCounter(Vowels.ALL).increment(1);
		for (IntWritable value : values)
		{
			sum += value.get();
		}
			result.set(sum);
			context.write(key, result); 
			}

}
