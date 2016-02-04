package org.hadoop.vimox;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class ZooDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException,InterruptedException,ClassNotFoundException{
		// TODO Auto-generated method stub

		if(args.length < 2){
			System.out.println("<input> <output>");
			System.err.println(1);
		}
		Job job = new Job();
		job.setJarByClass(ZooDriver.class);
		job.setJobName("ZOO CATAGORIZEd");
		
		job.setMapperClass(ZooMapper.class);
		job.setReducerClass(ZooReducer.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		
		
		if (job.waitForCompletion(true)) {
            System.out.println("--------------------------------\nJob Done Successfully\n--------------------------------");
            System.exit(0);
        } else {
            System.out.println("--------------------------------\nSome Error Occured\n--------------------------------");
            System.exit(1);
        }
		
	
	
	}

}
