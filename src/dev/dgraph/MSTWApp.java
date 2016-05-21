// Topic 29. Generate minimum spanning tree on weighted graphs (14.1)

package dev.dgraph;

class Edge{
	public int srcVert;
	public int destVert;
	public int distance;
	
	public Edge(int sv, int dv, int distance){
		this.srcVert = sv;
		this.destVert = dv;
		this.distance = distance;
	}
}

class PriorityQ{
	private final int SIZE = 20;
	private Edge[] queArray;
	private int size;
	
	public PriorityQ(){
		queArray = new Edge[SIZE];
		size = 0;
	}
	
	// insert item in sorting order
	public void insert(Edge item){
		int j;
		for(j=0; j<size; j++){
			if(item.distance >= queArray[j].distance)
				break;
		}
		
		for(int k=size-1; k>=j; k--)
			queArray[k+1] = queArray[k];
		
		queArray[j] = item;
		size++;
	}
	
	// remove minimum item
	public Edge removeMin(){
		return queArray[--size];
	}
	
	// remove item at n
	public void removeN(int n){
		for(int j=n; j<size-1; j++)
			queArray[j] = queArray[j+1];
		size--;
	}
	
	// peek at minimum item
	public Edge peekMin(){
		return queArray[size-1];
	}
	
	// return queue size
	public int size(){
		return size;
	}
	
	public boolean isEmpty(){
		return (size == 0);
	}
	
	public Edge peekN(int n){
		return queArray[n];
	}
	
	// find item with specified destVert value
	public int find(int findDex){
		for(int j=0; j<size; j++){
			if(queArray[j].destVert == findDex)
				return j;
		}
		return -1;
	}
}

class Graph{
	private final int MAX_VERTS = 20;
	private final int INFINITY = 100000;
	// list of vertices
	private Vertex[] vertexList;
	private int[][] adjMat;
	// current number of vertices
	private int nVerts;
	private int currentVert;
	private PriorityQ thePQ;
	// number of verts in tree
	private int nTree;
	
	public Graph(){
		vertexList = new Vertex[MAX_VERTS];
		adjMat = new int[MAX_VERTS][MAX_VERTS];
		nVerts = 0;
		for(int i=0; i<MAX_VERTS; i++)
			for(int j=0; j<MAX_VERTS; j++)
				adjMat[i][j] = INFINITY;
		thePQ = new PriorityQ();
	}
	
	public void addVertex(char lab){
		vertexList[nVerts++] = new Vertex(lab);
	}
	
	public void addEdge(int start, int end, int weight){
		adjMat[start][end] = weight;
		adjMat[end][start] = weight;
	}
	
	public void displayVertex(int n){
		System.out.print(vertexList[n].label);
	}
	
	public void mstw(){
		// start at 0
		currentVert = 0;
		
		// while not all verts in tree
		while(nTree < nVerts - 1){
			// put current vertex in tree
			vertexList[currentVert].isInTree = true;
			nTree++;
			
			// insert edges adjacent to currentVert into PQ
			for(int i=0; i<nVerts; i++){
				// skip if this is current vert
				if(i == currentVert)
					continue;
				// skip if this vertex is already in tree
				if(vertexList[i].isInTree)
					continue;
				int distance = adjMat[currentVert][i];
				// skip if no edge
				if(distance == INFINITY)
					continue;
				putInPQ(i, distance);
			}
			
			if(thePQ.size() == 0){
				System.err.println("Graph not connected");
				return;
			}
			
			// remove edge with minimum distance, from PQ
			Edge theEdge = thePQ.removeMin();
			int sourceVert = theEdge.srcVert;
			currentVert = theEdge.destVert;
			
			// display edge from source to current
			System.out.print(vertexList[sourceVert].label);
			System.out.print(vertexList[currentVert].label);
			System.out.print(" ");
		}
		
		// mst is complete, unmark all vertices
		for(int i=0; i<nVerts; i++)
			vertexList[i].isInTree = false;
	}
	
	public void putInPQ(int newVert, int newDist){
		// is there another edge with the same destination vertex?
		int queueIndex = thePQ.find(newVert);
		if(queueIndex != -1){
			Edge tempEdge = thePQ.peekN(queueIndex);
			int oldDist = tempEdge.distance;
			// if new edge is shorter, remove old edge and insert new edge
			if(oldDist > newDist){
				thePQ.removeN(queueIndex);
				Edge theEdge = new Edge(currentVert, newVert, newDist);
				thePQ.insert(theEdge);
			}
			// else no action, just leave the old vertex there
		}
		// no edge with same destination vertex, insert new one
		else{
			Edge theEdge = new Edge(currentVert, newVert, newDist);
			thePQ.insert(theEdge);
		}
	}
}


public class MSTWApp {

	public static void main(String[] args) {
		Graph graph = new Graph();
		
		graph.addVertex('A');
		graph.addVertex('B');
		graph.addVertex('C');
		graph.addVertex('D');
		graph.addVertex('E');
		graph.addVertex('F');
		
		graph.addEdge(0, 1, 6);
		graph.addEdge(0, 3, 4);
		graph.addEdge(1, 2, 10);
		graph.addEdge(1, 3, 7);
		graph.addEdge(1, 4, 7);
		graph.addEdge(2, 3, 8);
		graph.addEdge(2, 4, 5);
		graph.addEdge(2, 5, 6);
		graph.addEdge(3, 4, 12);
		graph.addEdge(4, 5, 7);
		
		System.out.print("Minimum spanning tree: ");
		graph.mstw();
		System.out.println();
	}
}
