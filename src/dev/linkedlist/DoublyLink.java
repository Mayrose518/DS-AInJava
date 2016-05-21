package dev.linkedlist;

public class DoublyLink {
	public int data;
	public DoublyLink next;
	public DoublyLink prev;
	
	public DoublyLink(int data){
		this.data = data;
	}
	
	public void displayLink(){
		System.out.print(data + " ");
	}
}
