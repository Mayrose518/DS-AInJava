// Topic 24: Implement a heap with functions as insertion, deletion and change (12.1)

package dev.heap;

class Node{
	private int data;
	public Node(int data){
		this.data = data;
	}
	
	public int getKey(){
		return this.data;
	}
	
	public void setKey(int data){
		this.data = data;
	}
}

class Heap{
	private Node[] heapArray;
	private int maxSize;
	private int currentSize;
	
	public Heap(int max){
		this.maxSize = max;
		currentSize = 0;
		heapArray = new Node[max];
	}
	
	public boolean isEmpty(){
		return (currentSize == 0);
	}
	
	public boolean insert(int key){
		if(currentSize == maxSize)
			return false;
		Node newNode = new Node(key);
		heapArray[currentSize] = newNode;
		trickleUp(currentSize);
		++currentSize;
		return true;
	}
	
	public void trickleUp(int index){
		int parent = (index - 1) / 2;
		Node bottom = heapArray[index];
		
		// max heap
		while(index > 0 && heapArray[parent].getKey() < bottom.getKey()){
			// move down
			heapArray[index] = heapArray[parent];
			index = parent;
			parent = (parent - 1) / 2;
		}
		
		heapArray[index] = bottom;
	}
	
	
	// delete item with maximum key
	public Node remove(){
		Node root = heapArray[0];
		heapArray[0] = heapArray[--currentSize];
		trickleDown(0);
		return root;
	}
	
	public void trickleDown(int index){
		int largerChild;
		// save root
		Node top = heapArray[index];
		
		// while node has at least one child 
		while(index < currentSize / 2){
			int leftChild = 2 * index + 1;
			int rightChild = leftChild + 1;
			
			// find larger child
			if(rightChild < currentSize && heapArray[leftChild].getKey() < heapArray[rightChild].getKey())
				largerChild = rightChild;
			else
				largerChild = leftChild;
			
			if(top.getKey() >= heapArray[largerChild].getKey())
				break;
			
			heapArray[index] = heapArray[largerChild];
			index = largerChild;
		}
		
		heapArray[index] = top;
	}
	
	public boolean change(int index, int newValue){
		if(index < 0 || index >= currentSize)
			return false;
		
		int oldValue = heapArray[index].getKey();
		heapArray[index].setKey(newValue);
		
		if(oldValue < newValue)
			trickleUp(index);
		else
			trickleDown(index);
		return true;
	}
	
	public void displayHeap(){
		System.out.print("heapArray: ");
		for(int m=0; m<currentSize; m++)
			if(heapArray[m] != null)
				System.out.print(heapArray[m].getKey() + " ");
			else
				System.out.print("-- ");
		System.out.println();
		
		int nBlanks = 32;
		int itemsPerRow = 1;
		int column = 0;
		int j = 0;
		String dots = "...........................";
		System.out.println(dots + dots);
		
		while(currentSize > 0){
			if(column == 0)
				for(int k=0; k<nBlanks; k++)
					System.out.print(" ");
			
			System.out.print(heapArray[j].getKey());
			if(++j == currentSize)
				break;
			
			if(++column == itemsPerRow){
				nBlanks /= 2;
				itemsPerRow *= 2;
				column = 0;
				System.out.println();
			}
			else
				for(int k=0; k<nBlanks*2-2; k++)
					System.out.print(" ");
		}
		System.out.println("\n" + dots + dots);
	}
}

public class HeapApp {

	public static void main(String[] args) {
		Heap heap = new Heap(31);
		
		heap.insert(70);
		heap.insert(40);
		heap.insert(50);
		heap.insert(20);
		heap.insert(60);
		heap.insert(100);
		heap.insert(80);
		heap.insert(30);
		heap.insert(10);
		heap.insert(90);
		
		heap.displayHeap();
		
		heap.remove();
		heap.displayHeap();
		
		heap.insert(5);
		heap.displayHeap();
		
		heap.change(3, 1);
		heap.displayHeap();
	}
}
