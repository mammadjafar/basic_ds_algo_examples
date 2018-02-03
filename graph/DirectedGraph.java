package graph;

public class DirectedGraph {
	
	private final int MAX_VERTS = 20;
	private Vertex vertexList[];
	private int adjMat[][];
	private int nVerts; // current number of vertices
	private char sortedArray[];
	
	public static void main (String [] args) {
		DirectedGraph theGraph = new DirectedGraph();
		theGraph.addVertex('A'); 
		theGraph.addVertex('B'); 
		theGraph.addVertex('C'); 
		theGraph.addVertex('D'); 
		theGraph.addVertex('E');
		theGraph.addVertex('F'); 
		theGraph.addVertex('G'); 
		theGraph.addVertex('H');
		
		theGraph.addEdge(0, 3); 
		theGraph.addEdge(0, 4); 
		theGraph.addEdge(1, 4); 
		theGraph.addEdge(2, 5); 
		theGraph.addEdge(3, 6); 
		theGraph.addEdge(4, 6); 
		theGraph.addEdge(5, 7); 
		theGraph.addEdge(6, 7);
		
		theGraph.topo();

	}
	
	public DirectedGraph() {
		vertexList = new Vertex[MAX_VERTS];
		adjMat = new int[MAX_VERTS][MAX_VERTS];
		nVerts = 0;
		for(int i=0; i<MAX_VERTS; i++)
			for(int j=0; j<MAX_VERTS; j++)
				adjMat[i][j]=0;
		sortedArray = new char[MAX_VERTS];
	}
	public void addVertex(char label) {
		vertexList[nVerts++] = new Vertex(label);
	}
	public void addEdge(int start, int end) {
		// wanna add some checks ?
		adjMat[start][end]=1;
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
	
	
	//	Step 1 -- Find a vertex that has no successors.
	//  Step 2 -- Delete this vertex from the graph, and insert its label at the beginning of a list.
	public void topo() {
		int originalNVerts = nVerts;
		int sor=0;
		while(nVerts > 0) {
			int currentVertex = noSuccessor();
			if(currentVertex == -1) {
				System.out.println("Error there's a cycle.");
				return;
			}
			sortedArray[sor++]=vertexList[currentVertex].label;
			deleteVertex(currentVertex);
		}
		System.out.println("Topological sorted array: ");
		for(int i=0; i<originalNVerts; i++)
			System.out.print(sortedArray[i] + " ");
		System.out.println();
	}
	
	public int noSuccessor() {
		boolean isEdge ;
		for(int row=0; row<nVerts; row++) {
			isEdge = false;
			for(int col=0; col<nVerts; col++){
				if(adjMat[col][row] > 0) {
					isEdge=true;
					break;
				}
			}
			if(!isEdge)
				return row;
		}
		return -1;
	}
	
	public void moveRowUp(int row, int length) {
		for(int col=0; col<length; col++)
			adjMat[row][col] = adjMat[row+1][col]; 
	}
	
	public void moveColLeft(int col, int length) {
		for(int row=0; row<length; row++)
			adjMat[row][col] = adjMat[row][col+1]; 
	}
	
	public void deleteVertex(int delVert) {
		if(delVert != nVerts-1){
			for(int j=delVert; j<nVerts-1; j++) {
				vertexList[j] = vertexList[j+1];
			}
			for(int i=delVert; i<nVerts-1; i++)
				moveRowUp(i, nVerts);
			
			for(int i=delVert; i<nVerts-1; i++)
				moveColLeft(i, nVerts-1);
		}
		nVerts--;
	}
	
}


