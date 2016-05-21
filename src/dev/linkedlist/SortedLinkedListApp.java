// Topic 9: Implement a sorted link list (5.6, 5.7)

package dev.linkedlist;

class SortedLinkedList{
	private Link head;
	
	public SortedLinkedList(){
		head = null;
	}
	
	public boolean isEmpty(){
		return (head == null);
	}
	
	public void insert(int key){
		Link link = new Link(key);
		Link prev = null;
		Link cur = head;
		
		while(cur != null && cur.data < key){
			prev = cur;
			cur = cur.next;
		}
		
		// empty list
		if(prev == null)
			head = link;
		// non-empty list
		else
			prev.next = link;
		link.next = cur;
	}
	
	public Link remove(){
		Link temp = head;
		head = head.next;
		return temp;
	}
	
	public void displayList(){
		System.out.print("Head --> Tail: ");
		Link cur = head;
		while(cur != null){
			cur.displayLink();
			cur = cur.next;
		}
		System.out.println();
	}
}

public class SortedLinkedListApp {

	public static void main(String[] args) {
		SortedLinkedList list = new SortedLinkedList();
		
		list.insert(20);
		list.insert(40);
		
		list.displayList();
		
		list.insert(10);
		list.insert(30);
		list.insert(50);
		
		list.displayList();
		
		list.remove();
		list.displayList();
	}

}
