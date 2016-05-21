// Topic 8: Implement a linked list with functions as insertion, deletion, find (5.1, 5.2, 5.3)

package dev.linkedlist;

class LinkedList{
	private Link head;
	
	public LinkedList(){
		head = null;
	}
	
	public boolean isEmpty(){
		return (head == null);
	}
	
	public Link getHead(){
		return head;
	}
	
	// ----------------- inserting ------------------------
	
	public void insertFirst(int key){
		Link link = new Link(key);
		link.next = head;
		head = link;
	}
	
	public void insertLast(int key){
		Link link = new Link(key);
		if(isEmpty())
			head = link;
		else{
			Link cur = head;
			while(cur.next != null)
				cur = cur.next;
			cur.next = link;
		}
	}
	
	public void insertAfter(int key, int inserted){
		Link insertedLink = new Link(inserted);
		if(isEmpty())
			head = insertedLink;
		else{
			Link cur = head;
			while(cur != null && cur.data != key)
				cur = cur.next;
			// found key
			if(cur != null){
				insertedLink.next = cur.next;
				cur.next = insertedLink;
			}
		}
	}
	
	public void insertBefore(int key, int inserted){
		Link insertedLink = new Link(inserted);
		if(isEmpty())
			head = insertedLink;
		else{
			Link prev = null;
			Link cur = head;
			while(cur != null && cur.data != key){
				prev = cur;
				cur = cur.next;
			}
			// found key
			if(cur != null){
				prev.next = insertedLink;
				insertedLink.next = cur;
			}
		}
	}
	
	// ----------------- deleting ------------------------
	
	public Link deleteFirst(){
		if(isEmpty()){
			System.err.println("List is empty, Nothing to delete!");
			return null;
		}
		else {
			Link temp = head;
			head = head.next;
			return temp;
		}
	}
	
	public Link delete(int key){
		if(isEmpty()){
			System.err.println("List is empty, Nothing to delete!");
			return null;
		}
		else if(head.data == key){
			return deleteFirst();
		}
		else {
			Link prev = null;
			Link cur = head;
			while(cur != null && cur.data != key){
				prev = cur;
				cur = cur.next;
			}
			// found
			if(cur != null){
				prev.next = cur.next;
				cur.next = null;
				return cur;
			}
		}
		return null;
	}
	
	// ----------------- find ------------------------
	
	public boolean find(int key){
		if(isEmpty()){
			System.err.println("List is empty!");
			return false;
		}
		else {
			Link cur = head;
			while(cur != null && cur.data != key)
				cur = cur.next;
			if(cur == null)
				return false;
			else
				return true;
		}
	}
	
	// ----------------- traversal ------------------------
	
	public void displayList(){
		System.out.println("First --> last");
		Link cur = head;
		while(cur != null){
			cur.displayLink();
			cur = cur.next;
		}
		System.out.println();
	}
	
//	public Link reverse(Link node){
//		if(node == null)
//			return null;
//		if(node.next == null)
//			return node;
//		Link next = node.next;
//		node.next = null;
//		Link reverseNode = reverse(next);
//		next.next = node;
//		return reverseNode;
//	}
}



public class LinkListApp {

	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		
		list.insertFirst(22);
		list.insertLast(44);
		list.insertLast(66);
		list.insertLast(88);
		list.insertLast(99);
		
		list.displayList();
		
//		Link head = list.reverse(list.getHead());
		
//		head.next.displayLink();
		
		list.insertAfter(22, 33);
		list.insertBefore(88, 77);
		
		list.displayList();
		
		list.deleteFirst();
		list.displayList();
		
		list.delete(66);
		list.displayList();
		
		System.out.println(list.find(88));
		System.out.println(list.find(66));
	}
}