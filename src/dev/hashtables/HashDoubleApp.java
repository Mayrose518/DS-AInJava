// Topic 22: Implement hash table with linear probing and double hashing (11.1, 11.2)

package dev.hashtables;

class DataItem{
	private int data;
	
	public DataItem(int data){
		this.data = data;
	}
	
	public int getData(){
		return this.data;
	}
}

class HashTable{
	private DataItem[] hashArray;
	private int arraySize;
	private DataItem nonItem;
	
	public HashTable(int size){
		this.arraySize = size;
		hashArray = new DataItem[arraySize];
		nonItem = new DataItem(-1);
	}
	
	public void displayTable(){
		System.out.print("Table: ");
		for(int i=0; i<arraySize; i++){
			if(hashArray[i] != null)
				System.out.print(hashArray[i].getData() + " ");
			else
				System.out.print("** ");
		}
		System.out.println();
	}
	
	public int hashFunc1(int key){
		return key % arraySize;
	}
	
	// double hashing
	public int hashFunc2(int key){
		return 5 - key % 5;
	}
	
	public void insert(DataItem item){
		int key = item.getData();
		int hashVal = hashFunc1(key);
		int stepSize = hashFunc2(key);
		
		// linear probing
		while(hashArray[hashVal] != null && hashArray[hashVal].getData() != -1){
			hashVal += stepSize;
			hashVal = hashVal % arraySize;
		}
		hashArray[hashVal] = item;
	}
	
	public DataItem delete(int key){
		int hashVal = hashFunc1(key);
		int stepSize = hashFunc2(key);
		
		while(hashArray[hashVal] != null){
			if(hashArray[hashVal].getData() == key){
				DataItem temp = hashArray[hashVal];
				hashArray[hashVal] = nonItem;
				return temp;
			}
			hashVal += stepSize;
			hashVal = hashVal % arraySize;
		}
		return null;
	}
	
	public DataItem find(int key){
		int hashVal = hashFunc1(key);
		int stepSize = hashFunc2(key);
		
		while(hashArray[hashVal] != null){
			if(hashArray[hashVal].getData() == key)
				return hashArray[hashVal];
			hashVal += stepSize;
			hashVal = hashVal % arraySize;
		}
		return null;
	}
}

public class HashDoubleApp {

	public static void main(String[] args) {
		int size = 30;
		HashTable table = new HashTable(size);
		// insert 50 data items
		int num = 15;
		for(int i=0; i<num; i++){
			int key = (int)(Math.random() * 100);
			DataItem item = new DataItem(key);
			table.insert(item);
		}
		
		table.displayTable();
		if(table.find(20) != null)
			System.out.println(table.find(20).getData());
		table.delete(20);
		table.displayTable();
	}
}