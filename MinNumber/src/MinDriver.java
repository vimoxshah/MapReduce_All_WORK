import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;



public class MinDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		Job job = new Job();

		job.setJarByClass(MinDriver.class);
		job.setJobName("Min Number");
		
		job.setMapperClass(MinMapper.class);
		job.setReducerClass(MinReducer.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
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


