package org.hadoop.avg.vimox;


import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class AverageMarks {
	
	public static void main(String[] args) 
			throws IOException, InterruptedException, ClassNotFoundException {
		
		if (args.length != 2) {
			
			System.err.println("usage: <input path> <output path>");
			System.exit(1);
		}
		
		Job job = new Job();

		job.setJarByClass(AverageMarks.class);
		job.setJobName("AverageMarks");
		
		job.setMapperClass(AverageMarksMapper.class);
		job.setReducerClass(AverageMarksReducer.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(DoubleWritable.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job,new Path(args[1]));
		
		if (job.waitForCompletion(true)) {
	       System.out.println("--------------------------------\nJob Done Successfully\n--------------------------------");
	       System.exit(0);
		}
		else {
			System.out.println("--------------------------------\nSome Error Occured\n--------------------------------");
			System.exit(1);
		}
	}

}

