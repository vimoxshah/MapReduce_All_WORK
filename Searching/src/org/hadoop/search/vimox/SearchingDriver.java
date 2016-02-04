package org.hadoop.search.vimox;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class SearchingDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException,InterruptedException,ClassNotFoundException {
		
		Configuration conf = new Configuration();
		conf.set("Searching", args[2]);
		
		Job job = Job.getInstance(conf, "Searchin in File");
		job.setJarByClass(SearchingDriver.class);
		
		job.setMapperClass(SearchingMapper.class);
		job.setReducerClass(SearchReducer.class);
		
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
