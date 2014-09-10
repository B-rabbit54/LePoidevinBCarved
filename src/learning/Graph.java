package learning;

public class Graph {
	private int [][] matrix; //for tracking edges
	private int [] marks; //for coloring
	private int edgeCount = 0;
	private int size;
	
	public Graph(int n) //n is the total number of vertices.
	{
		matrix = new int[n][n];
		marks = new int [n];
		size = n;
		//makeVertices(n);
	}
	public void resetMarks()
	{
		marks = new int[size];
	}
	//first Neighbor next Neighbor
	
	private void makeVertices(int n) {
		int vertexID = 0;
		for(int row = 0; row < n; row++)
		{
			for (int column = 0; column < n; column++)
			{
				matrix[row][column] = new Vertex(vertexID++, row, column).id;
			}
		}	
	}

	public int vcount()
	{
		return marks.length;
	}
	
	public int first(int v){
		for( int i = 0; i < vcount(); i++)
		{
			if(matrix[v][i] != 0)
			{
				return i;
			}
		}
		return vcount();
	}
	public int next(int vertex, int lastVisitedNeighbor)
	{
		for(int i = lastVisitedNeighbor + 1; i < vcount(); i++)
		{
			if(matrix[vertex][i] != 0)
			{
				return i;//matrix[vertex][i];
			}
		}
		return vcount();
	}
	public void addEdge(int vertex, int neighbor, int weight)
	{
		matrix[vertex][neighbor] = weight;
		//matrix[neighbor][vertex] = weight;
		edgeCount++;
	}
	public int ecount()
	{
		return edgeCount;
	}
	public void removeEdge(int vertex, int neighbor)
	{
		matrix[vertex][neighbor] = 0; 
		matrix[neighbor][vertex] = 0;
		edgeCount--;
		
	}  
	public boolean isEdge(int vertex, int neighbor)
	{
		return matrix[vertex][neighbor] != 0;
	}
	
	public int getMark(int v)
	{
		return marks[v];
	}
	
	public void setmark(int vertex, int mark)
	{
		marks[vertex] = mark;
	}
}
