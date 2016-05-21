// Topic 18: Implement Partitioning and Quick sort (7.2, 7.3, 7.4)

package dev.sort;

class ArrayPar{
	private int[] array;
	private int nElems;
	
	public ArrayPar(int max){
		array = new int[max];
		nElems = 0;
	}
	
	public void insert(int value){
		array[nElems++] = value;
	}
	
	public int size(){
		return nElems;
	}
	
	public void display(){
		System.out.print("Array = ");
		for(int val : array)
			System.out.print(val + " ");
		System.out.println();
	}
	
	// returns the pivot index
	public int partition(int left, int right, int pivot){
		// start from the leftmost element
		int leftPtr = left;
		// start from the two less elements from rightmost(exclude one is right, one is pivot)
		int rightPtr = right - 2;
		
		// start partitioning, until left pointer and right pointer meet
		while(true){
			// find bigger item
			while(array[leftPtr] < pivot)
				++leftPtr;
			// find smaller item
			while(array[rightPtr] > pivot)
				--rightPtr;
			// if pointers across, partition done
			if(leftPtr >= rightPtr)
				break;
			else
				swap(leftPtr, rightPtr);
		}
		// restore pivot location to middle
		swap(leftPtr, right-1);
		return leftPtr;
	}
	
	private void swap(int i, int j){
		int temp;
		temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public void quickSort(){
		recQuickSort(0, nElems-1);
	}
	
	private void recQuickSort(int left, int right){
		// if size <= 1, already sorted
		if(right - left <= 0)
			return;
		else{
			// use rightmost element as pivot
//			int pivot = array[right];
			
			// use median of 3 to determine the pivot
			int size = right - left + 1;
			// array size <= 3, manual sort
			if(size <= 3)
				manualSort(left, right);
			// if array size <= 10, use the insertion sort
			else if(size <10){
				insertionSort(left, right);
			}
			else{
				int pivot = medianOf3(left, right);
				
				int partition = partition(left, right, pivot);
				recQuickSort(left, partition - 1);
				recQuickSort(partition + 1, right);
			}
		}
	}
	
	private void insertionSort(int left, int right){
		int in, out;
		for(out=left+1; out<=right; out++){
			int temp = array[out];
			in = out;
			while(in > left && array[in-1] >= temp){
				// move each element forward
				array[in-1] = array[in];
				--in;
			}
			array[in] = temp;
		}
	}
	
	private int medianOf3(int left, int right){
		int center = (left + right) / 2;
		
		// sort the 3 elements
		if(array[left] > array[center])
			swap(left, center);
		if(array[left] > array[right])
			swap(left, right);
		if(array[center] > array[right])
			swap(center, right);
		
		// swap pivot on one element of rightmost, because median always less than rightmost
		swap(center, right-1);
		// return median value
		return array[right-1];
	}
	
	private void manualSort(int left, int right){
		int size = right - left + 1;
		if(size <= 1)
			return;
		if(size == 2){
			if(array[left] > array[right])
				swap(left, right);
			return;
		}
		else{
			if(array[left] > array[right-1])
				swap(left, right-1);
			if(array[left] > array[right])
				swap(left, right);
			if(array[right] < array[right-1])
				swap(right, right-1);
		}
	}
}

public class QuickSortApp {

	public static void main(String[] args) {
		int maxSize = 16;
		ArrayPar array = new ArrayPar(maxSize);
		for(int i=0; i<maxSize; i++){
			int num = (int)(Math.random() * 199);
			array.insert(num);
		}
		
		array.display();
		
		//int pivot = 99;
		//System.out.println("Pivot is " + pivot);
		array.quickSort();
		array.display();
	}
}