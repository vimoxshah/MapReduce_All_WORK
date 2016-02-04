package org.hadoop.character.vimox;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class CharDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args)  throws ClassNotFoundException, IOException,InterruptedException{
		// TODO Auto-generated method stub
		
		Job job = new Job();
		job.setJarByClass(CharDriver.class);
		job.setJobName("Charater Count");
		job.setMapperClass(CharMapper.class);
		job.setReducerClass(CharReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
		

	}

}
