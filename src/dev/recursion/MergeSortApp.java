// Topic 15: Implement mergeSort (6.6)

package dev.recursion;

class Array{
	private int[] arr;
	private int nElems;
	
	public Array(int maxSize){
		arr = new int[maxSize];
		nElems = 0;
	}
	
	public void insert(int value){
		arr[nElems] = value;
		nElems++;
	}
	
	public void display(){
		for(int i=0; i<nElems; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
	
	public void mergeSort(){
		int[] workSpace = new int[nElems];
		doMergeSort(workSpace, 0, nElems-1);
	}
	
	public void doMergeSort(int[] workSpace, int lowerBound, int higherBound){
		if(lowerBound == higherBound)
			return;
		else{
			int mid = (lowerBound + higherBound) / 2;
			doMergeSort(workSpace, lowerBound, mid);
			doMergeSort(workSpace, mid+1, higherBound);
			merge(workSpace, lowerBound, mid+1, higherBound);
		}
	}
	
	private void merge(int[] workSpace, int lower, int higher, int upperBound){
		int i = 0;
		int lowerBound = lower;
		int num = upperBound - lowerBound + 1;
		int mid = higher - 1;
		
		while(lower <= mid && higher <= upperBound){
			if(arr[lower] < arr[higher])
				workSpace[i++] = arr[lower++];
			else
				workSpace[i++] = arr[higher++];
		}
		
		while(lower <= mid)
			workSpace[i++] = arr[lower++];
		
		while(higher <= upperBound)
			workSpace[i++] = arr[higher++];
		
		for(int k=0; k<num; k++)
			arr[lowerBound+k] = workSpace[k];
	}
}

public class MergeSortApp {

	public static void main(String[] args) {
		int maxSize = 100;
		Array arr = new Array(maxSize);
		
		arr.insert(64);
		arr.insert(21);
		arr.insert(33);
		arr.insert(70);
		arr.insert(12);
		arr.insert(85);
		arr.insert(44);
		arr.insert(3);
		arr.insert(99);
		arr.insert(0);
		arr.insert(108);
		arr.insert(36);
		
		arr.display();
 		arr.mergeSort();
		
		arr.display();
	}

}
