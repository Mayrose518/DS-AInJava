// Topic 23. Implement hash table with separate chaining (11.3)

package dev.hashtables;

class Link{
	private int data;
	public Link next;
	
	public Link(int data){
		this.data = data;
	}
	
	public int getData(){
		return this.data;
	}
	
	public void displayLink(){
		System.out.print(data + " ");
	}
}

class SortedList{
	private Link first;
	
	public SortedList(){
		first = null;
	}
	
	// insert link in order
	public void insert(Link link){
		int key = link.getData();
		Link previous = null;
		Link current = first;
		
		while(current != null && current.getData() < key){
			previous = current;
			current = current.next;
		}
		
		if(previous == null)
			first = link;
		else
			previous.next = link;
		link.next = current;
	}
	
	public void delete(int key){
		Link previous = null;
		Link current = first;
		
		while(current != null && key != current.getData()){
			previous = current;
			current = current.next;
		}
		
		// beginning of list
		if(previous == null)
			first = first.next;
		else
			previous.next = current.next;
	}
	
	public Link find(int key){
		Link current = first;
		while(current != null && current.getData() <= key){
			if(current.getData() == key)
				return current;
			current = current.next;
		}
		return null;
	}
	
	public void displayList(){
		System.out.print("List (first --> last): ");
		Link current = first;
		while(current != null){
			current.displayLink();
			current = current.next;
		}
		System.out.println();
	}
}

class HashTableChain{
	private SortedList[] hashArray;
	private int arraySize;
	
	public HashTableChain(int size){
		this.arraySize = size;
		hashArray = new SortedList[arraySize];
		for(int i=0; i<arraySize; i++){
			hashArray[i] = new SortedList();
		}
	}
	
	public void displayTable(){
		for(int i=0; i<arraySize; i++){
			System.out.print(i + ". ");
			hashArray[i].displayList();
		}
	}
	
	public int hashFunc(int key){
		return key % arraySize;
	}
	
	public void insert(Link link){
		int key = link.getData();
		int hashVal = hashFunc(key);
		hashArray[hashVal].insert(link);
	}
	
	public void delete(int key){
		int hashVal = hashFunc(key);
		hashArray[hashVal].delete(key);
	}
	
	public Link find(int key){
		int hashVal = hashFunc(key);
		Link link = hashArray[hashVal].find(key);
		return link;
	}
}

public class HashChainApp {

	public static void main(String[] args) {
		int size = 10;
		HashTableChain table = new HashTableChain(size);
		int num = 100;
		for(int i=0; i<num; i++){
			int key = (int)(Math.random() * 100);
			Link dataItem = new Link(key);
			table.insert(dataItem);
		}
		
		table.displayTable();
		table.insert(new Link(99));
		
		table.displayTable();
		if(table.find(99) != null)
			System.out.println("Found 99");
		
		table.delete(99);
		table.displayTable();
	}
}