// Topic 1: Implement an ordered array with insertion, deletion, display and find with binary search (2.4)

package dev.array;

class Array{
	private int[] a;
	private int nElems;
	
	public Array(int max){
		a = new int[max];
		nElems = 0;
	}
	
	public int size(){
		return nElems;
	}
	
	public int find(int searchKey){
		// return nElems if not found
		int lowerBound = 0;
		int upperBound = nElems - 1;
		int middle;
		
		// non-recursive binary search
		while(true){
			middle = (lowerBound + upperBound) / 2;
			if(a[middle] == searchKey)
				return middle;
			else if(lowerBound > upperBound)
				return nElems;
			else{
				if(searchKey < a[middle])
					upperBound = middle - 1;
				else
					lowerBound = middle + 1;
			}
		}
	}
	
	public void insert(int value){
		// find a place to insert
		int i;
		for(i=0; i<nElems; i++)
			if(value < a[i])
				break;
		// move the rest elements
		for(int j=nElems; j>=i; j--)
			a[j+1] = a[j];
		a[i] = value;
		nElems++;
	}
	
	public boolean delete(int value){
		int pos = find(value);
		if(pos == nElems)
			return false;
		else{
			for(int i=pos; i<nElems; i++)
				a[i] = a[i+1];
			--nElems;
			return true;
		}
	}
	
	public void display(){
		for(int i=0; i<nElems; i++)
			System.out.print(a[i] + " ");
		System.out.println();
	}
}

public class ArrayApp {

	public static void main(String[] args) {
		int maxSize = 100;
		Array arr;
		arr = new Array(maxSize);
		
		arr.insert(77);
		arr.insert(99);
		arr.insert(44);
		arr.insert(55);
		arr.insert(22);
		arr.insert(88);
		arr.insert(11);
		arr.insert(66);
		arr.insert(33);
		
		int searchKey = 55;
		if(arr.find(searchKey) != arr.size())
			System.out.println("Found " + searchKey);
		else
			System.out.println("Cannot find " + searchKey);
		
		arr.display();
		
		arr.delete(55);
		arr.delete(99);
		
		arr.display();
	}
}