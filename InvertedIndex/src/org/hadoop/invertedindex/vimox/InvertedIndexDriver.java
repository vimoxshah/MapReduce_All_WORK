package org.hadoop.invertedindex.vimox;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class InvertedIndexDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception{
		
		Job job = new Job();
		job.setJobName("Inverted INdex");
		job.setJarByClass(InvertedIndexDriver.class);
		
		job.setMapperClass(InvertedIndexMapper.class);
		job.setReducerClass(InvertedIndexReducer.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		 
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		if(job.waitForCompletion(true)){
			System.out.println("job done");
		}
		else{
			System.out.println("Something wrong");
		}
	
	}

}
