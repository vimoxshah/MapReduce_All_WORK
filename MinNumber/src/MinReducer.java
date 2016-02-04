import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class MinReducer extends Reducer<Text, IntWritable, NullWritable, IntWritable> {
	
	private static IntWritable min = new IntWritable();
	
	public void reduce(Text key,Iterable<IntWritable> values,Context context)throws IOException,InterruptedException{
		
		int minVal =19 ;
		for(IntWritable value : values){
			
			if(value.get()<=minVal){
				minVal=value.get();
				context.write(NullWritable.get(), new IntWritable(minVal));
			
			}
		
		}
	}

}
