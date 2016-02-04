import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class MinMapper extends Mapper<Object, Text, Text, IntWritable> {

	private static Text id = new Text();
	private static IntWritable num = new IntWritable();
	
	public void map(Object key,Text value,Context context) throws IOException,InterruptedException{
		
		StringTokenizer str = new StringTokenizer(value.toString(),",");
		
		
			id.set((str.nextToken()));
		
			str.nextToken();
			
			num.set(Integer.parseInt(str.nextToken()));
			
			context.write(id, num);
			
		}
	}

