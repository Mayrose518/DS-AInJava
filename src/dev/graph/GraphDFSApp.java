// Topic 26: Implement a graph with dfs search (13.1)

package dev.graph;

import java.util.*;

class DFSGraph{
	private int MAX_VERTS = 20;
	private Vertex vertexList[];
	private int adjMat[][];
	private int nVerts;
	private Stack<Integer> stack;
	
	public DFSGraph(){
		vertexList = new Vertex[MAX_VERTS];
		adjMat = new int[MAX_VERTS][MAX_VERTS];
		nVerts = 0;
		for(int i=0; i<MAX_VERTS; i++){
			for(int j=0; j<MAX_VERTS; j++)
				adjMat[i][j] = 0;
		}
		stack = new Stack<Integer>();
	}
	
	public void addVertex(char lab){
		vertexList[nVerts++] = new Vertex(lab);
	}
	
	public void addEdge(int start, int end){
		adjMat[start][end] = 1;
		adjMat[end][start] = 1;
	}
	
	public void displayVertex(int v){
		System.out.print(vertexList[v].label);
	}
	
	public void dfs(){
		vertexList[0].wasVisited = true;
		displayVertex(0);
		stack.push(0);
		
		while(!stack.isEmpty()){
			int v = getAdjUnvisitedVertex(stack.peek());
			if(v == -1)
				stack.pop();
			else{
				vertexList[v].wasVisited = true;
				displayVertex(v);
				stack.push(v);
			}
		}
		
		// stack is empty, reset flags
		for(int j=0; j<nVerts; j++)
			vertexList[j].wasVisited = false;
	}
	
	// return an unvisited vertex next to v
	public int getAdjUnvisitedVertex(int v){
		for(int i=0; i<nVerts; i++)
			if(adjMat[v][i] == 1 && !vertexList[i].wasVisited)
				return i;
		return -1;
	}
}

public class GraphDFSApp {

	public static void main(String[] args) {
		DFSGraph graph = new DFSGraph();
		graph.addVertex('A');
		graph.addVertex('B');
		graph.addVertex('C');
		graph.addVertex('D');
		graph.addVertex('E');
		
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(0, 3);
		graph.addEdge(3, 4);
		
		System.out.print("Visits: ");
		graph.dfs();
		System.out.println();
	}
}
