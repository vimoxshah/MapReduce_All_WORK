package org.hadoop.movierating.vimox;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MovieReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	
	private Map<String, Integer> countMap = new HashMap<String,Integer>();
	
	public void reduce(Text key,Iterable<IntWritable> values,Context context)throws IOException,InterruptedException{
		
		
		for(IntWritable value : values){
		countMap.put(key.toString(),value.get());
	
		}
		System.out.println(countMap);
	}

	//@Override
	protected void cleanup(org.apache.hadoop.mapreduce.Reducer.Context context)
			throws IOException, InterruptedException {
		
		
		int min = Integer.MAX_VALUE;	
		String key="";
		
		for (Map.Entry<String, Integer> e : countMap.entrySet()) {
		   String  mapKey = e.getKey();
		    int value = e.getValue();
		    if(value==0){
		    	break;
		    }
		    if(value < min){
				min= value;
				key=mapKey;
				System.out.println("key = " + key+ "min value= "+min);
				
		    }
		    }
		context.write(key, min);
		
		
		super.cleanup(context);
	}
	
}


