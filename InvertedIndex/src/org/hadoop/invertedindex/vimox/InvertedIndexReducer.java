package org.hadoop.invertedindex.vimox;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class InvertedIndexReducer extends
		Reducer<Text, Text, Text, Text> {
	

	
	public void reduce(Text key,Iterable<Text> values,Context context) throws IOException,InterruptedException{
		
		Map<String, Integer> fileCount = new HashMap<String, Integer>();
		

		for(Text value: values){
			
				Integer count =fileCount.get(value.toString());
				
				fileCount.put(value.toString(), (count==null) ? 1 : count+1);
				
		
		
		}
		context.write(key, new Text(fileCount.toString()));
	}

}
