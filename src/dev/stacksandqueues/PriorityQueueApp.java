// Topic 7: Implement a priority queue (4.6)

package dev.stacksandqueues;

class PriorityQueue{
	private int maxSize;
	private int[] queueArray;
	private int nItems;
	
	public PriorityQueue(int maxSize){
		this.maxSize = maxSize;
		queueArray = new int[maxSize];
		nItems = 0;
	}
	
	public void insert(int element){
		int j;
		if(nItems == 0)
			queueArray[nItems++] = element;
		else{
			for(j=nItems-1; j>=0; j--){
				if(element > queueArray[j])
					queueArray[j+1] = queueArray[j];
				else
					break;
			}
			queueArray[j+1] = element;
			++nItems;
		}
	}
	
	public int remove(){
		return queueArray[--nItems];
	}
	
	public int peekMin(){
		return queueArray[nItems-1];
	}
	
	public boolean isEmpty(){
		return (nItems == 0);
	}
	
	public boolean isFull(){
		return (nItems == maxSize);
	}
}

public class PriorityQueueApp {

	public static void main(String[] args) {
		PriorityQueue theQueue = new PriorityQueue(5);
		
		theQueue.insert(30);
		theQueue.insert(50);
		theQueue.insert(10);
		theQueue.insert(40);
		theQueue.insert(20);
		
		while(!theQueue.isEmpty()){
			System.out.print(theQueue.remove() + " ");
		}
	}

}
