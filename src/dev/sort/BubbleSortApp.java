// Topic 2: Implement bubble sort (3.1)

package dev.sort;

class ArrayBub{
	private int[] a;
	private int nElems;
	
	public ArrayBub(int max){
		a = new int[max];
		nElems = 0;
	}
	
	public void insert(int value){
		a[nElems] = value;
		++nElems;
	}
	
	public void display(){
		for(int i=0; i<nElems; i++)
			System.out.print(a[i] + " ");
		System.out.println();
	}
	
	public void bubbleSort(){
		for(int outer=nElems-1; outer>1; outer--)
			for(int inner=0; inner<outer; inner++)
				if(a[inner] > a[inner+1])
					swap(inner, inner+1);
	}
	
	public void swap(int i, int j){
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}

public class BubbleSortApp {

	public static void main(String[] args) {
		int maxSize = 100;
		ArrayBub arr;
		arr = new ArrayBub(maxSize);
		
		arr.insert(77);
		arr.insert(99);
		arr.insert(44);
		arr.insert(55);
		arr.insert(22);
		arr.insert(88);
		arr.insert(11);
		arr.insert(66);
		arr.insert(33);
		
		arr.display();
		
		arr.bubbleSort();
		
		arr.display();
	}
}
