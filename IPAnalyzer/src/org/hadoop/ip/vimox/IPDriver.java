package org.hadoop.ip.vimox;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class IPDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException , InterruptedException,ClassNotFoundException {
		// TODO Auto-generated method stub

		Job job = new Job();
		job.setJarByClass(IPDriver.class);
		job.setJobName("IP Analyzer");
		job.setNumReduceTasks(7);
		
		job.setMapperClass(IPMapper.class);
		job.setReducerClass(IPReducer.class);
		job.setCombinerClass(IPReducer.class);
		job.setPartitionerClass(IPPartioner.class);
		
		
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		if(job.waitForCompletion(true)){
			System.out.println("Job done successfully");
		}
		else{
			System.out.println("Something went wrong");
		}
		}

}
