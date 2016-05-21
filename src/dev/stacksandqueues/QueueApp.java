// topic 6: Implement a queue (circular) with array using front rear pointers (4.4)

package dev.stacksandqueues;

class Queue{
	private int maxSize;
	private int[] queueArray;
	private int front, rear, nItems;
	
	public Queue(int maxSize){
		this.maxSize = maxSize;
		queueArray = new int[maxSize];
		front = 0;
		rear = -1;
		nItems = 0;
	}
	
	public void insert(int element){
		if(rear == maxSize - 1)
			rear = -1;
		queueArray[++rear] = element;
		nItems++;
	}
	
	public int remove(){
		if(!isEmpty()){
			int temp = queueArray[front++];
			// wrap around
			if(front == maxSize)
				front = 0;
			nItems--;
			return temp;
		}
		else {
			System.err.println("The queue is empty!");
			return -1;
		}
		
	}
	
	public int peekFront(){
		if(!isEmpty()){
			return queueArray[front];
		}
		else{
			System.err.println("The queue is empty!");
			return -1;
		}
	}
	
	public boolean isEmpty(){
		return (nItems == 0);
	}
	
	public boolean isFull(){
		return (nItems == maxSize);
	}
}

public class QueueApp {

	public static void main(String[] args) {
		Queue theQueue = new Queue(5);
		
		theQueue.insert(10);
		theQueue.insert(20);
		theQueue.insert(30);
		theQueue.insert(40);
		
		theQueue.remove();
		theQueue.remove();
		theQueue.remove();
		
		theQueue.insert(50);
		theQueue.insert(60);
		theQueue.insert(70);
		theQueue.insert(80);
		
		while(!theQueue.isEmpty()){
			System.out.println("PeekFront: " + theQueue.peekFront());
			System.out.println("Remove: " + theQueue.remove());
		}
	}

}
