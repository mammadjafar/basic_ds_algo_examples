package graph;

import java.util.PriorityQueue;
import java.util.Stack;

public class Graph {
	
	private final int MAX_VERTS = 20;
	private Vertex vertexList[];
	private int adjMat[][];
	private int nVerts; // current number of vertices
	private Stack<Integer> theStack = new Stack<Integer>() ;
	private PriorityQueue<Integer> theQueue = new PriorityQueue<>();
	
	
	public static void main (String [] args) {
		Graph theGraph = new Graph();
		theGraph.addVertex('A'); 
		theGraph.addVertex('B'); 
		theGraph.addVertex('C'); 
		theGraph.addVertex('D'); 
		theGraph.addVertex('E');
		
		theGraph.addEdge(0, 1);
		theGraph.addEdge(1, 2); 
		theGraph.addEdge(0, 4); 
		theGraph.addEdge(3, 4);
		
		System.out.println("Visits: depth-first search");
		theGraph.dfs();   
		System.out.println();
		System.out.println("Visits: breadth-first search");
		theGraph.bfs();   
		System.out.println();
		System.out.println("Minimum spanning tree edges");
		theGraph.mst();   
		System.out.println();
	}
	
	public Graph() {
		vertexList = new Vertex[MAX_VERTS];
		adjMat = new int[MAX_VERTS][MAX_VERTS];
		nVerts = 0;
		for(int i=0; i<MAX_VERTS; i++)
			for(int j=0; j<MAX_VERTS; j++)
				adjMat[i][j]=0;
	}
	public void addVertex(char label) {
		vertexList[nVerts++] = new Vertex(label);
	}
	public void addEdge(int start, int end) {
		// wanna add some checks ?
		adjMat[start][end]=1;
		adjMat[end][start]=1;
	}
	public void displayVertex(int v) {
		System.out.print(vertexList[v].label);
	}
	
	public int getAdjUnvisitedVertex(int v) {
		for(int i=0; i< MAX_VERTS; i++){
			if(adjMat[v][i] == 1 && !vertexList[i].wasVisited)
				return i;
		}
		return -1;
	}
	
	public void dfs() {
		vertexList[0].wasVisited = true;
		displayVertex(0);
		theStack.push(0);
		while(!theStack.isEmpty()){
			int v = getAdjUnvisitedVertex((int)theStack.peek());
			if(v == -1)
				theStack.pop();
			else {
				vertexList[v].wasVisited=true;
				displayVertex(v);
				theStack.push(v);
			}
		}
		// we are done with depth first search
		for(int i=0; i<nVerts; i++)
			vertexList[i].wasVisited=false;
	}
	
	public void bfs() {
		vertexList[0].wasVisited = true;
		displayVertex(0);
		theQueue.add(0);
		while(!theQueue.isEmpty()){
			int p = (int) theQueue.poll();
			
			while(getAdjUnvisitedVertex(p) != -1) {
				int v = getAdjUnvisitedVertex(p);
				vertexList[v].wasVisited=true;
				displayVertex(v);
				theQueue.add(v);
			}
		}
		// we are done with depth first search
		for(int i=0; i<nVerts; i++)
			vertexList[i].wasVisited=false;
	}
	
	// minimum spanning tree
	// we can do it with dfs or bfs. just first time reach path
	public void mst() {

		vertexList[0].wasVisited = true;
		theStack.push(0);
		while(!theStack.isEmpty()){
			int curr = (int)theStack.peek();
			int v = getAdjUnvisitedVertex(curr);
			if(v == -1)
				theStack.pop();
			else {
				vertexList[v].wasVisited=true;
				displayVertex(curr); System.out.print("->"); displayVertex(v);
				System.out.println();
				theStack.push(v);
			}
		}
		// we are done with depth first search
		for(int i=0; i<nVerts; i++)
			vertexList[i].wasVisited=false;
	
	}

}


