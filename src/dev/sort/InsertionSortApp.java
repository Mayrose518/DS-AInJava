// Topic 4: Implement insertion sort (3.3)

package dev.sort;

class ArrayIns{
	private int[] a;
	private int nElems;
	
	public ArrayIns(int max){
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
	
	public void insertSort(){
		int outer, inner;
		for(outer=1; outer<nElems; outer++){
			int temp = a[outer];
			inner = outer;
			while(inner > 0 && temp <= a[inner-1]){
				a[inner] = a[inner-1];
				--inner;
			}
			a[inner] = temp;
		}
	}
}

public class InsertionSortApp {

	public static void main(String[] args) {
		int maxSize = 100;
		ArrayIns arr;
		arr = new ArrayIns(maxSize);
		
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
		
		arr.insertSort();
		
		arr.display();
	}

}
