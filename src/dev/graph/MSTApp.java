// Topic 28. Generate minimum spanning tree (13.3)

package dev.graph;

import java.util.*;

class MSTGraph{
	private final int MAX_VERTS = 20;
	private Vertex[] vertexList;
	private int adjMat[][];
	private int nVerts;
	private Stack<Integer> stack;
	
	public MSTGraph(){
		vertexList = new Vertex[MAX_VERTS];
		nVerts = 0;
		adjMat = new int[MAX_VERTS][MAX_VERTS];
		for(int i=0; i<MAX_VERTS; i++)
			for(int j=0; j<MAX_VERTS; j++)
				adjMat[i][j] = 0;
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
	
	public int getAdjUnvisitedVertex(int v){
		for(int i=0; i<nVerts; i++)
			if(adjMat[v][i] == 1 && !vertexList[i].wasVisited)
				return i;
		return -1;
	}
	
	public void mst(){
		vertexList[0].wasVisited = true;
		stack.push(0);
		
		while(!stack.isEmpty()){
			int currentVertex = stack.peek();
			int v = getAdjUnvisitedVertex(currentVertex);
			if(v == -1)
				stack.pop();
			else{
				vertexList[v].wasVisited = true;
				stack.push(v);
				
				displayVertex(currentVertex);
				displayVertex(v);
				System.out.print(" ");
			}
		}
		
		for(int i=0; i<nVerts; i++){
			vertexList[i].wasVisited = false;
		}
	}
}

public class MSTApp {

	public static void main(String[] args) {
		MSTGraph graph = new MSTGraph();
		graph.addVertex('A');
		graph.addVertex('B');
		graph.addVertex('C');
		graph.addVertex('D');
		graph.addVertex('E');
		
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(0, 3);
		graph.addEdge(0, 4);
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		graph.addEdge(1, 4);
		graph.addEdge(2, 3);
		graph.addEdge(2, 4);
		graph.addEdge(3, 4);
		
		System.out.print("Minimum spanning tree: ");
		graph.mst();
		System.out.println();
	}
}