package learning;

import java.util.ArrayList;
import java.util.List;

public class TopologicalSort {
//	/** Recursive topological sort */
//	static void topsort(Graph G) {
//	for (int i=0; i<G.n(); i++) // Initialize Mark array
//	G.setMark(i, UNVISITED);
//	for (int i=0; i<G.n(); i++) // Process all vertices
//	if (G.getMark(i) == UNVISITED)
//	tophelp(G, i); // Recursive helper function
//	}
//	/** Topsort helper function */
//	static void tophelp(Graph G, int v) {
//	G.setMark(v, VISITED);
//	for (int w = G.first(v); w < G.n(); w = G.next(v, w))
//	if (G.getMark(w) == UNVISITED)
//	tophelp(G, w);
//	printout(v); // PostVisit for Vertex v
//	}
	int VISITED = 1;
	int UNVISITED = 0;
	
	List<Integer> list = new ArrayList<Integer>();
	
	public List<Integer> topSort(Graph G)
 {
		for (int i = 0; i < G.vcount(); i++) // Initialize Mark array
		{
			G.setmark(i, UNVISITED);
			for (int j = 0; j < G.vcount(); j++) // Process all vertices
			{
				if (G.getMark(j) == UNVISITED)
					tophelp(G, j); // Recursive helper function
			}
		}
		return list;
	}

	/** Topsort helper function */
	public void tophelp(Graph G, int v) 
	{
		G.setmark(v, VISITED);
		for (int w = G.first(v); w < G.vcount(); w = G.next(v, w))
		{
			if (G.getMark(w) == UNVISITED)
				tophelp(G, w);
		}
		list.add(v);
	}

}
