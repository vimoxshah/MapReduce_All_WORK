package org.hadoop.ip.vimox;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class IPPartioner extends Partitioner<Text, NullWritable> {
	

	@Override
	public int getPartition(Text key, NullWritable value, int numberOfReducer) {
		
		String[] row = key.toString().split("\u0004");
		String[] ipAddresses = row[0].split("\\.");
		String ipPrefix = ipAddresses[0];
		
		if(numberOfReducer == 0){
			return 0;
			}
		if(ipPrefix.equals("192") || ipPrefix.equals("172") || ipPrefix.equals("10")){
			return 0;
			}
		if(ipPrefix.equals("41") || ipPrefix.equals("102") || ipPrefix.equals("105")){
			return 1 % numberOfReducer;
			}
		if(ipPrefix.equals("81") || ipPrefix.equals("217") || ipPrefix.equals("62")){
			return 2 % numberOfReducer;
			}
		if(ipPrefix.equals("200") ){
			return 3 % numberOfReducer;
			}
		if(ipPrefix.equals("9") ){
			return 4 % numberOfReducer;
			}
		if(ipPrefix.equals("17") ){
			return 5 % numberOfReducer;
			}
		else{
			return 6 % numberOfReducer;
		}
		
	}

}
