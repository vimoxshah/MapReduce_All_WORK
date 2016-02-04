package org.hadoop.counter.vimox;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class CounterDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException,ClassNotFoundException ,InterruptedException {
		// TODO Auto-generated method stub
		
		Job job = new Job();
		job.setJarByClass(CounterDriver.class);
		job.setJobName("Counter Vowels");
		
		job.setMapperClass(CounterMapper.class);
		job.setReducerClass(CounterReducer.class);
		job.setCombinerClass(CounterReducer.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		job.setNumReduceTasks(4);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
		

	}

}
