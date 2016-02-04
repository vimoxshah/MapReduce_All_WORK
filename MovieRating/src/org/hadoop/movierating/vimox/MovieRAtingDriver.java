package org.hadoop.movierating.vimox;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MovieRAtingDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		

		Job job = new Job();
		job.setJarByClass(MovieRAtingDriver.class);
		job.setJobName("Movie Rating");
		
		job.setMapperClass(MovieRatingMapper.class);
		job.setReducerClass(MovieRatingReducer.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
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
