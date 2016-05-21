// Topic 20. Implement a binary tree with functions as find, insertion, deletion, traverse (8.1)

package dev.tree;

import java.util.Stack;

class Node{
	int data;
	Node leftChild;
	Node rightChild;
	
	public Node(int data){
		this.data = data;
	}
	
	public void displayNode(){
		System.out.print(data + " ");
	}
}

class Tree{
	private Node root;
	
	public Tree(){
		root = null;
	}
	
	public Node getRoot(){
		return root;
	}
	
	// Non-recursive find
	public Node find(int key){
		Node current = root;
		while(current.data != key){
			if(key < current.data)
				current = current.leftChild;
			else
				current = current.rightChild;
			// down to leaf
			if(current == null)
				return null;
		}
		return current;
	}

	
	public void insert(int key){
		Node node = new Node(key);
		// null tree
		if(root == null){
			root = node;
			return;
		}
		
		else{
			Node current = root;
			Node parent;
			while(true){
				parent = current;
				if(key < current.data){
					current = current.leftChild;
					if(current == null){
						parent.leftChild = node;
						return;
					}
				}
				else{
					current = current.rightChild;
					if(current == null){
						parent.rightChild = node;
						return;
					}
				}
			}
		}
	}
	
	// delete node with given key
	public boolean delete(int key){
		Node current = root;
		Node parent = root;
		boolean isLeftChild = true;
		
		// find node to delete
		while(current.data != key){
			parent = current;
			if(key < current.data){
				current = current.leftChild;
				isLeftChild = true;
			}
				
			else{
				current = current.rightChild;
				isLeftChild = false;
			}
			// not find node to delete
			if(current == null)
				return false;
		}
		
		// Now found node to delete
		// Case 1: leaf node, just delete it
		if(current.leftChild == null && current.rightChild == null){
			// if root
			if(current == root)
				root = null;
			else if(isLeftChild){
				parent.leftChild = null;
			}
			else
				parent.rightChild = null;
		}
		
		// Case 2: node has one child
		// Node only has leftChild, remove it and substitute with its left subtree
		else if(current.rightChild == null){
			if(current == root)
				root = current.leftChild;
			else if(isLeftChild)
				parent.leftChild = current.leftChild;
			else
				parent.rightChild = current.leftChild;
		}
		
		//Node only has rightChild, remove it and substitute with its right subtree
		else if(current.leftChild == null){
			if(current == root)
				root = current.rightChild;
			else if(isLeftChild)
				parent.leftChild = current.rightChild;
			else
				parent.rightChild = current.rightChild;
		}
		
		// Case 3: node has two children
		// replace with its successor
		else{
			// get successor node
			Node successor = getSuccessor(current);
			
			// connect parent of current to successor instead
			if(current == root)
				root = successor;
			else if(isLeftChild)
				parent.leftChild = successor;
			else
				parent.rightChild = successor;
			
			// connect successor to current's left child
			successor.leftChild = current.leftChild;
			// successor cannot have a leftChild
		}
		return true;
	}
	
	// The successor node of a delNode is the node which has next highest value
	private Node getSuccessor(Node delNode){
		Node successorParent = delNode;
		Node successor = delNode;
		Node current = delNode.rightChild;
		
		// delNode's successor node is delNode's rightChild's leftmost child
		while(current != null){
			successorParent = successor;
			successor = current;
			current = current.leftChild;
		}
		
		// make connections if successor is not right child of delNode
		if(successor != delNode.rightChild){
			successorParent.leftChild = successor.rightChild;
			successor.rightChild = delNode.rightChild;
		}
		return successor;
	}
	
	// Below are recursive methods for 3 traverses
	public void preOrderTraverse(Node localRoot){
		if(localRoot != null){
			localRoot.displayNode();
			preOrderTraverse(localRoot.leftChild);
			preOrderTraverse(localRoot.rightChild);
		}
	}
	
	public void inOrderTraverse(Node localRoot){
		if(localRoot != null){
			inOrderTraverse(localRoot.leftChild);
			localRoot.displayNode();
			inOrderTraverse(localRoot.rightChild);
		}
	}
	
	public void postOrderTraverse(Node localRoot){
		if(localRoot != null){
			postOrderTraverse(localRoot.leftChild);
			postOrderTraverse(localRoot.rightChild);
			localRoot.displayNode();
		}
	}
	
	public void displayTree(){
		Stack<Node> globalStack = new Stack<Node>();
		globalStack.push(root);
		int nBlanks = 32;
		boolean isRowEmpty = false;
		while(!isRowEmpty){
			Stack<Node> localStack = new Stack<Node>();
			isRowEmpty = true;
			
			for(int j=0; j<nBlanks; j++)
				System.out.print(' ');
			
			while(!globalStack.isEmpty()){
				Node temp = globalStack.pop();
				if(temp != null){
					System.out.print(temp.data);
					localStack.push(temp.leftChild);
					localStack.push(temp.rightChild);
					
					if(temp.leftChild != null || temp.rightChild != null)
						isRowEmpty = false;
				}
				else{
					System.out.print("--");
					localStack.push(null);
					localStack.push(null);
				}
				for(int j=0; j<nBlanks*2-2; j++)
					System.out.print(' ');
			}
			System.out.println();
			nBlanks /= 2;
			while(!localStack.isEmpty())
				globalStack.push(localStack.pop());
		}
	}
}

public class TreeApp {

	public static void main(String[] args) {
		Tree tree = new Tree();
		tree.insert(50);
		tree.insert(25);
		tree.insert(75);
		tree.insert(12);
		tree.insert(37);
		tree.insert(43);
		tree.insert(30);
		tree.insert(33);
		tree.insert(87);
		tree.insert(93);
		tree.insert(97);
		
		// test insertion and display
		tree.displayTree();
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		// test find
		Node found = tree.find(43);
//		Node notFound = tree.find(98);
		found.displayNode();
//		notFound.displayNode();
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		// test traversals
		tree.preOrderTraverse(tree.getRoot());
		System.out.println();
		tree.inOrderTraverse(tree.getRoot());
		System.out.println();
		tree.postOrderTraverse(tree.getRoot());
		System.out.println();
			
		// test deletion
		// case 1: delete leaf node
		tree.delete(33);
		tree.displayTree();
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		// case 2-1: delete node has left child
		tree.delete(25);
		tree.displayTree();
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		// case 2-2: delete node has right child
		tree.delete(93);
		tree.displayTree();
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		// case 3: delete node has both children
		tree.delete(30);
		tree.displayTree();
	}
}
