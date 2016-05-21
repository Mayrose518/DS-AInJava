// Topic 5: Implement a stack with array (4.1)

package dev.stacksandqueues;

class StackX{
	private int maxSize;
	private int[] stackArray;
	private int top;
	
	public StackX(int s){
		this.maxSize = s;
		stackArray = new int[s];
		top = -1;
	}
	
	public void push(int element){
		if(!isFull())
			stackArray[++top] = element;
		else{
			System.err.println("Stack is full!");
			return;
		}
	}
	
	public int pop(){
		if(!isEmpty())
			return stackArray[top--];
		else{
			System.err.println("Stack is empty!");
			return -1;
		}
	}
	
	public int peek(){
		if(!isEmpty())
			return stackArray[top];
		else{
			System.err.println("Stack is empty!");
			return -1;
		}
	}
	
	public boolean isEmpty(){
		return (top == -1);
	}
	
	public boolean isFull(){
		return (top == maxSize - 1);
	}
}

public class StackApp {

	public static void main(String[] args) {
		StackX theStack = new StackX(10);
		theStack.push(20);
		theStack.push(40);
		theStack.push(60);
		theStack.push(80);
		
		while(!theStack.isEmpty()){
			System.out.println("Peek " + theStack.peek());
			System.out.println("Pop " + theStack.pop());
		}
	}

}
