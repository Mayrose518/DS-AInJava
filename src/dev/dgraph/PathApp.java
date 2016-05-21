// Topic 30. (Dijkstra's algorithm) Find shortest path with weighted, directed graphs (14.2)

package dev.dgraph;

class DGraph{
	private final int MAX_VERTS = 20;
	private final int INFINITY = 100000;
	private Vertex vertexList[];
	private int adjMat[][];
	private int nVerts;
	private int nTree;
	private DistPar[] sPath;
	private int currentVert;
	private int startToCurrent;
	
	public DGraph(){
		vertexList = new Vertex[MAX_VERTS];
		adjMat = new int[MAX_VERTS][MAX_VERTS];
		nVerts = 0;
		nTree = 0;
		for(int i=0; i<MAX_VERTS; i++)
			for(int j=0; j<MAX_VERTS; j++)
				adjMat[i][j] = INFINITY;
		sPath = new DistPar[MAX_VERTS];
	}
	
	public void addVertex(char lab){
		vertexList[nVerts++] = new Vertex(lab);
	}
	
	public void addEdge(int start, int end, int weight){
		adjMat[start][end] = weight;
	}
	
	// find all shortest paths
	public void path(){
		int startTree = 0;
		vertexList[startTree].isInTree = true;
		nTree = 1;
		
		// transfer row of distance from adjMat to sPath
		for(int i=0; i<nVerts; i++){
			int tempDist = adjMat[startTree][i];
			sPath[i] = new DistPar(startTree, tempDist);
		}
		
		// until all vertices are in the tree
		while(nTree < nVerts){
			int indexMin = getMin();
			int minDist = sPath[indexMin].distance;
			
			if(minDist == INFINITY){
				System.out.println("There are unreachable vertices");
				break;
			}
			else{
				currentVert = indexMin;
				startToCurrent = sPath[indexMin].distance;
				// minimum distance from startTree is to currentVert, and is startToCurrent
			}
			
			// put current vertex in tree
			vertexList[currentVert].isInTree = true;
			nTree++;
			adjust_sPath();
		}
		
		displayPaths();
		
		nTree = 0;
		for(int i=0; i<nVerts; i++)
			vertexList[i].isInTree = false;
	}
	
	public int getMin(){
		int minDist = INFINITY;
		int indexMin = 0;
		for(int i=1; i<nVerts; i++){
			if(!vertexList[i].isInTree && sPath[i].distance < minDist){
				minDist = sPath[i].distance;
				indexMin = i;
			}
		}
		return indexMin;
	}
	
	public void adjust_sPath(){
		// adjust values in shortest-path sPath
		int column = 1;
		while(column < nVerts){
			// if this column's vertex already in tree, skip it
			if(vertexList[column].isInTree){
				column++;
				continue;
			}
			
			// calculate distance for one sPath entry
			// get edge from currentVert to column
			int currentToFringe = adjMat[currentVert][column];
			// add distance from start
			int startToFringe = startToCurrent + currentToFringe;
			// get distance of current sPath entry
			int sPathDist = sPath[column].distance;
			
			// compare distance from start with sPath entry
			if(startToFringe < sPathDist){
				sPath[column].parentVert = currentVert;
				sPath[column].distance = startToFringe;
			}
			column++;
		}
	}
	
	public void displayPaths(){
		for(int j=0; j<nVerts; j++){
			System.out.print(vertexList[j].label + "=");
			if(sPath[j].distance == INFINITY)
				System.out.print("inf");
			else
				System.out.print(sPath[j].distance);
			char parent = vertexList[sPath[j].parentVert].label;
			System.out.print("(" + parent + ") ");
		}
		System.out.println();
	}
}

public class PathApp {
	public static void main(String[] args){
		DGraph theGraph = new DGraph();
		theGraph.addVertex('A');
		theGraph.addVertex('C');
		theGraph.addVertex('B');
		theGraph.addVertex('D');
		theGraph.addVertex('E');
		
		theGraph.addEdge(0, 1, 50);
		theGraph.addEdge(0, 3, 80);
		theGraph.addEdge(1, 2, 60);
		theGraph.addEdge(1, 3, 90);
		theGraph.addEdge(2, 4, 40);
		theGraph.addEdge(3, 2, 20);
		theGraph.addEdge(3, 4, 70);
		theGraph.addEdge(4, 1, 50);
		
		System.out.println("Shortest paths: ");
		theGraph.path();
		System.out.println();
	}
}