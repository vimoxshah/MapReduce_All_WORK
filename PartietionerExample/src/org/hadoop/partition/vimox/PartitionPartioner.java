package org.hadoop.partition.vimox;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class PartitionPartioner extends Partitioner<Text, IntWritable> {

	

	@Override
	public int getPartition(Text key, IntWritable value, int numReduceTasks) {
		// TODO Auto-generated method stub
		
		String word = key.toString();
		
		if(numReduceTasks == 0){
		return 0;
		}
		if(word.substring(0, 1).equals("a") || word.substring(0, 1).equals("A") ){
			return 0;
		}
		else if(word.substring(0, 1).equals("e") || word.substring(0, 1).equals("E") ){
			return 1%numReduceTasks;
		}
		else if(word.substring(0, 1).equals("i") || word.substring(0, 1).equals("I") ){
			return 2%numReduceTasks;
		}
		else if(word.substring(0, 1).equals("o") || word.substring(0, 1).equals("O") ){
			return 3%numReduceTasks;
		}
		else if(word.substring(0, 1).equals("u") || word.substring(0, 1).equals("U") ){
			return 4%numReduceTasks;
		}
		else{
			return 5%numReduceTasks;
		}
		
	
	}

}
