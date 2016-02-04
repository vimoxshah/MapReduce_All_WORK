package org.hadoop.partition.vimox;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;


public class PartitionDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException , InterruptedException , ClassNotFoundException{
		// TODO Auto-generated method stub
		
		Job job = new Job();
		job.setJarByClass(PartitionDriver.class);
		job.setJobName("Simple Partition Example");
		
		
		
		job.setMapperClass(PartitionMapper.class);
		
		job.setPartitionerClass(PartitionPartioner.class);
		 
	    job.setReducerClass(PartitionReducer.class);
	    job.setNumReduceTasks(6);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));		
		
		if(job.waitForCompletion(true)){
			System.out.println("Job done");
		}
		else{
			System.out.println("Something wrong");
		}
	}

}
