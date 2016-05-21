// Topic 27: Implement a graph with bfs search (13.2)

package dev.graph;

import java.util.PriorityQueue;
import java.util.Queue;

class BFSGraph{
	private final int MAX_VERTS = 20;
	private Vertex vertexList[];
	private int[][] adjMat;
	private int nVerts;
	private Queue<Integer> queue;
	
	public BFSGraph(){
		vertexList = new Vertex[MAX_VERTS];
		nVerts = 0;
		adjMat = new int[MAX_VERTS][MAX_VERTS];
		for(int i=0; i<MAX_VERTS; i++)
			for(int j=0; j<MAX_VERTS; j++)
				adjMat[i][j] = 0;
		
		queue = new PriorityQueue<Integer>();
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
	
	public void bfs(){
		vertexList[0].wasVisited = true;
		displayVertex(0);
		queue.add(0);
		int v2;
		
		while(!queue.isEmpty()){
			int v1 = queue.remove();
			while((v2 = getAdjUnvisitedVertex(v1)) != -1){
				vertexList[v2].wasVisited = true;
				displayVertex(v2);
				queue.add(v2);
			}
		}
		
		// queue is empty, reset flags
		for(int i=0; i<nVerts; i++)
			vertexList[i].wasVisited = false;
	}
	
	public int getAdjUnvisitedVertex(int v){
		for(int i=0; i<nVerts; i++)
			if(adjMat[i][v] == 1 && !vertexList[i].wasVisited)
				return i;
		return -1;
	}
	
}

public class GraphBFSApp {

	public static void main(String[] args) {
		BFSGraph graph = new BFSGraph();
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
		graph.bfs();
		System.out.println();
	}
}
