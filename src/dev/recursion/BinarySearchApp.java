// Topic 13: Recursive binary Search (6.3)

package dev.recursion;

class OrdArray{
	private static int[] arr;
	private static int nElems;
	
	public OrdArray(int max){
		arr = new int[max];
		nElems = 0;
	}
	
	public void display(){
		for(int i=0; i<nElems; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
	
	public void insert(int element){
		int i;
		for(i=0; i<nElems; i++){
			if(element < arr[i])
				break;
		}
		
		for(int j=nElems; j>i; j--)
			arr[j] = arr[j-1];
		
		arr[i] = element;
		nElems++;
	}
	
	public static int find(int value){
		return recFind(value, 0, nElems-1);
	}
	
	private static int recFind(int value, int lower, int upper){
		int mid = (lower + upper) / 2;
		// find value, return index
		if(value == arr[mid])
			return mid;
		// lower > upper, cannot find
		else if(lower > upper)
			return -1;
		// value at left side
		else if(value < arr[mid])
			return recFind(value, lower, mid-1);
		// value at right side
		else
			return recFind(value, mid+1, upper);
	}
}

public class BinarySearchApp {

	public static void main(String[] args) {
		int maxSize = 100;
		OrdArray arr = new OrdArray(maxSize);
		
		arr.insert(72);
		arr.insert(90);
		arr.insert(45);
		arr.insert(126);
		arr.insert(54);
		arr.insert(99);
		arr.insert(144);
		arr.insert(27);
		arr.insert(135);
		arr.insert(81);
		arr.insert(18);
		arr.insert(108);
		arr.insert(9);
		arr.insert(117);
		arr.insert(63);
		arr.insert(36);
		
		arr.display();
		
		System.out.print("Please input number to find: ");
		int key = Util.getInt();
		System.out.println("Index of " + key + " : " + OrdArray.find(key));
	}
}