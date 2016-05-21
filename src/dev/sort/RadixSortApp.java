// Topic 19: Implement Radix sort (Exercise 7.5)

package dev.sort;

import java.util.*;

public class RadixSortApp {
	public static final int RADIX = 10;
	
	public static void radixSort(int[] input){
		// create a multi-list bucket
		List<Integer>[] buckets = new List[RADIX];
		for (int k = 0; k < buckets.length; k++) {
			buckets[k] = new ArrayList<Integer>();
		}
		
		boolean maxLength = false;
		int temp = -1, placement = 1;
		while(!maxLength){
			maxLength = true;
			// loop through each element in input array
			for(int i=0; i<input.length; i++){
				// get each digit for each element
				temp = input[i] / placement;
				// add the element to corresponding bucket based on digit
				buckets[temp % RADIX].add(input[i]);
				// until all elements/placement equals to 0, it means all elements reach their maxLength
				// the loop below will never enter, so maxLength stays true, next time breaks out the outer loop, method ends
				if(maxLength && temp > 0)
					// for jump out the while loop
					maxLength = false;
			}
			
			int a = 0;
			// collect all elements from each bucket, put them back to input array
			for(int j=0; j<RADIX; j++){
				for(Integer i : buckets[j])
					input[a++] = i;
				// clear each bucket, waiting for next round use
				buckets[j].clear();
			}
			
			placement *= RADIX;
		}
	}

	public static void main(String[] args) {
		int input[] = {34, 186, 90, 32, 139, 43, 14, 146, 17, 146, 103, 147, 110, 99, 6, 166};
		radixSort(input);
		for(int value : input)
			System.out.print(value + " ");
		System.out.println();
	}
}
