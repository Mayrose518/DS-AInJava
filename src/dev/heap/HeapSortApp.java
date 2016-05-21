// Topic 25. Implement heap sort (12.2)

package dev.heap;

class HeapSort{
	private Node[] heapArray;
	private int maxSize;
	private int currentSize;
	
	public HeapSort(int max){
		this.maxSize = max;
		currentSize = 0;
		heapArray = new Node[max];
	}
	
	public Node remove(){
		Node root = heapArray[0];
		heapArray[0] = heapArray[--currentSize];
		trickleDown(0);
		return root;
	}
	
	public void trickleDown(int index){
		int largerChild;
		Node top = heapArray[index];
		
		while(index < currentSize / 2){
			int leftChild = index * 2;
			int rightChild = leftChild + 1;
			
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
	
	public void displayHeap(){
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
	
	public void displayArray(){
		for(int i=0; i<maxSize; i++){
			System.out.print(heapArray[i].getKey() + " ");
		}
		System.out.println();
	}
	
	public void insertAt(int index, Node newNode){
		heapArray[index] = newNode;
	}
	
	public void incrementSize(){
		currentSize++;
	}
}

public class HeapSortApp {

	public static void main(String[] args) {
		int size = 15;
		HeapSort heap = new HeapSort(size);
		for(int i=0; i<size; i++){
			int random = (int)(Math.random() * 100);
			Node newNode = new Node(random);
			heap.insertAt(i, newNode);
			heap.incrementSize();
		}
		
		heap.displayArray();
		
		// heapify
		for(int j=size/2-1; j>=0; j--){
			heap.trickleDown(j);
		}
		
		System.out.print("Heap: ");
		heap.displayArray();
		heap.displayHeap();
		
		for(int k=size-1; k>=0; k--){
			Node biggestNode = heap.remove();
			heap.insertAt(k, biggestNode);
		}
		
		System.out.print("Sorted: ");
		heap.displayArray();
	}
}