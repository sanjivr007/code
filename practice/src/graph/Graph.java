package graph;

import java.util.LinkedList;

public class Graph {
	
	private int V;
	private LinkedList<Integer> adj[];
	Graph(int v){
		V=v;
		adj= new LinkedList[V];
		for(int i=0;i<V;i++)
			adj[i]= new LinkedList<>();
	}
	
	void addEdge(int v,int w)
    {
        adj[v].add(w);
    }
	

	public static void main(String args[]){
		
		
	}
}
