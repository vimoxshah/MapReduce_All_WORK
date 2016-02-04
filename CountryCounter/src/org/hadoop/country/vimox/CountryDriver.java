package org.hadoop.country.vimox;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class CountryDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args)throws IOException,InterruptedException,ClassNotFoundException {
		// TODO Auto-generated method stub

		Job job= new Job();
		job.setJarByClass(CountryDriver.class);
		
		job.setJobName("Country Counter");
		
		job.setMapperClass(CountryMapper.class);
		job.setReducerClass(CountryReducer.class);
		job.setCombinerClass(CountryReducer.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		if(job.waitForCompletion(true)){
			System.out.println("Job Done");
		}
		else{
			System.out.println("Something went wrong");
		}
		
		
	}

}
