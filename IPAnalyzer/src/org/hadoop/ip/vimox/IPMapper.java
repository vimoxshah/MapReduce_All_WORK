package org.hadoop.ip.vimox;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class IPMapper extends Mapper<Object, Text, Text, NullWritable>{
	
	private  Text row = new Text();
	
	public void map(Object key, Text value,Context context) throws IOException,InterruptedException{
		
		StringTokenizer str = new StringTokenizer(value.toString(),"\n");
		
		while(str.hasMoreTokens()){
			row.set(str.nextToken());
			context.write(row, NullWritable.get());
		}
		
	}

}
