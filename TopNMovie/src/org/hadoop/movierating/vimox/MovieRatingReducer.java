
package org.hadoop.movierating.vimox;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class MovieRatingReducer
		extends
		Reducer<Text, IntWritable, Text, DoubleWritable> {
	
	private	TreeMap<String , Double> movie = new TreeMap<String, Double>();
		
		public void reduce(Text key,Iterable<IntWritable> values,Context context)throws IOException,InterruptedException{
		
		double sum=0;
		double len=0;
		for(IntWritable value : values){
			sum+=value.get();
			len++;
		}
		double avgRating= sum/len;
		movie.put(key.toString(), avgRating);
	//	System.out.println(movie);

		}
		
		
		 public static <K, V extends Comparable<V>> Map<K, V> 
		    sortByValues(final Map<K, V> map) {
			 
		    Comparator<K> valueComparator = new Comparator<K>() {
		      
		    	public int compare(K k1, K k2) {
		        int compare = 
		              map.get(k2).compareTo(map.get(k1));
		        if (compare == 0) 
		          return 1;
		        else 
		          return compare;
		      }
		    };
		 
		    Map<K, V> sortedByValues = new TreeMap<K, V>(valueComparator);
		    sortedByValues.putAll(map);
		    return sortedByValues;
		  }

		@Override
		protected void cleanup(
				org.apache.hadoop.mapreduce.Reducer.Context context)
				throws IOException, InterruptedException {
			
			// Calling the method sortByvalues
		    Map sortedMap = sortByValues(movie);
		 
		    // Get a set of the entries on the sorted map
		    Set set = sortedMap.entrySet();
		 
		    // Get an iterator
		    Iterator i = set.iterator();
		 
		    // Display elements
		    double rating = 0;
		    String movieid="";
		    int k=0;
		    while(i.hasNext()) {
		    	Map.Entry me = (Map.Entry)i.next();
		    	movieid= me.getKey().toString();
		    	rating = Double.parseDouble(me.getValue().toString());
		      
			 //     System.out.print(movieid + ": ");
			  //    System.out.println(rating);
			  
		     context.write(movieid, new DoubleWritable(rating));
		     k++;
		     if(k>2){
		    	 break;
		     }
		    }
		    
			super.cleanup(context);
		}
}
