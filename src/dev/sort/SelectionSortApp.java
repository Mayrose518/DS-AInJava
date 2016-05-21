// Topic 3: Implement selection sort (3.2)

package dev.sort;

class ArraySel{
	private int[] a;
	private int nElems;
	
	public ArraySel(int max){
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
	
	public void selectionSort(){
		int outer, inner, min;
		for(outer=0; outer<nElems-1; outer++){
			min = outer;
			for(inner=outer+1; inner<nElems; inner++){
				if(a[inner] < a[min])
					min = inner;
			}
			swap(min, outer);	
		}
	}
	
	public void swap(int i, int j){
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}


public class SelectionSortApp {

	public static void main(String[] args) {
		int maxSize = 100;
		ArraySel arr;
		arr = new ArraySel(maxSize);
		
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
		
		arr.selectionSort();
		
		arr.display();
	}

}
