package org.hadoop.vimox;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class ZooMapper extends Mapper<Object, Text, Text, IntWritable> {
	
    private static final String[] PROPERTIES = new String[]{"Name", "hair", "feathers", "eggs", "milk", "airborne", "aquatic", "predator", "toothed", "backbone", "breathes", "venomous", "fins", "*legs", "tail", "domestic", "catsize", "type"};
    private static final IntWritable ONE = new IntWritable(1);
    private static Text currentProperty = new Text();
	
	public void map(Object key , Text value, Context context) throws IOException,InterruptedException{
		String line = value.toString();
		if (!(line.isEmpty() || line.charAt(0) != '%' && line.charAt(0) != '@' && line.charAt(0) != '{')) {
			System.out.println("Igonring garbage Values");
		}
		else{
			StringTokenizer st = new StringTokenizer(line, ",");
			
			if(st.hasMoreTokens()){
				String propertyValue = st.nextToken();
				int currentPropertyIndex = 1;
				
				while(st.hasMoreTokens()){
					propertyValue = st.nextToken();
					if(propertyValue.equals("true")){
						currentProperty.set("number of animals with property: " + PROPERTIES[currentPropertyIndex]);
						context.write(currentProperty,ONE);
					}
					else if(!(propertyValue.equals(false))){
						propertyValue = currentPropertyIndex ==13 ? "number of animal with legs=" + propertyValue+ ":" : "number of animals with type =" + propertyValue+ ":" ;
						currentProperty.set(propertyValue);
						context.write(currentProperty, ONE);
					}
					++currentPropertyIndex;
				}
			}
		}
		
	}

}
