// Topic 10: Implement a doubly linked list (5.8)

package dev.linkedlist;

class DoublyLinkedList{
	private DoublyLink head;
	private DoublyLink tail;
	
	public DoublyLinkedList(){
		head = null;
		tail = null;
	}
	
	public boolean isEmpty(){
		return (head == null);
	}
	
	// ----------------- inserting ------------------------
	
	public void insertFirst(int key){
		DoublyLink link = new DoublyLink(key);
		
		if(isEmpty())
			head = tail = link;
		else {
			link.next = head;
			head.prev = link;
			head = link;
		}
	}
	
	public void insertLast(int key){
		DoublyLink link = new DoublyLink(key);
		
		if(isEmpty())
			head = tail = link;
		else{
			tail.next = link;
			link.prev = tail;
			tail = link;
		}
	}
	
	public boolean insertAfter(int key, int inserted){
		DoublyLink insertedLink = new DoublyLink(inserted);
		
		DoublyLink cur = head;
		while(cur != null && cur.data != key)
			cur = cur.next;
		// cannot find key
		if(cur == null)
			return false;
		// insert tail
		else if(cur == tail){
			insertedLink.next = null;
			tail = insertedLink;
		}
		// find key
		else {
			insertedLink.next = cur.next;
			cur.next.prev = insertedLink;
		}
		
		cur.next = insertedLink;
		insertedLink.prev = cur;
		return true;
	}
	
	// ----------------- deleting ------------------------
	
	public DoublyLink deleteFirst(){
		// empty list
		if(isEmpty()){
			System.err.println("list is empty!");
			return null;
		}
		
		// list has only 1 element
		else if(head.next == null)
			tail = null;
		
		else
			head.next.prev = null;
		
		DoublyLink temp = head;
		head = head.next;
		return temp;
	}
	
	public DoublyLink deleteLast(){
		// empty list
		if(isEmpty()){
			System.err.println("list is empty!");
			return null;
		}
		
		// list has only 1 element
		else if(head.next == null)
			head = null;
		
		else
			tail.prev.next = null;
		
		DoublyLink temp = tail;
		tail = tail.prev;
		return temp;
	}
	
	public DoublyLink deleteKey(int key){
		// empty list
		if(isEmpty()){
			System.err.println("list is empty!");
			return null;
		}
		
		DoublyLink cur = head;
		while(cur != null && cur.data != key)
			cur = cur.next;
		// cannot find
		if(cur == null)
			return null;
		else if(cur == head)
			return deleteFirst();
		else if(cur == tail)
			return deleteLast();
		else{
			cur.next.prev = cur.prev;
			cur.prev.next = cur.next;
			return cur;
		}
	}
	
	// ----------------- traversal ------------------------
	
	public void displayForward(){
		System.out.print("head --> tail: ");
		DoublyLink cur = head;
		
		while(cur != null){
			cur.displayLink();
			cur = cur.next;
		}
		
		System.out.println();
	}
	
	public void displayBackward(){
		System.out.print("tail --> head: ");
		DoublyLink cur = tail;
		
		while(cur != null){
			cur.displayLink();
			cur = cur.prev;
		}
		
		System.out.println();
	}
}

public class DoublyLinkedListApp {

	public static void main(String[] args) {
		DoublyLinkedList list = new DoublyLinkedList();
		
		list.insertFirst(22);
		list.insertFirst(44);
		list.insertFirst(66);
		
		list.insertLast(11);
		list.insertLast(33);
		list.insertLast(55);
		
		list.displayBackward();
		list.displayForward();
		
		list.deleteFirst();
		list.deleteLast();
		
		list.displayForward();
		list.deleteKey(11);
		
		list.displayForward();
		list.deleteKey(44);
		list.deleteKey(33);
		
		list.displayForward();
		
		list.insertAfter(22, 77);
		list.insertAfter(77, 88);
		
		list.displayForward();
	}
}
