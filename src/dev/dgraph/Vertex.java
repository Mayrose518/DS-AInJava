package dev.dgraph;

public class Vertex {
	public char label;
	public boolean isInTree;
	
	public Vertex(char lab){
		this.label = lab;
		isInTree = false;
	}
}
