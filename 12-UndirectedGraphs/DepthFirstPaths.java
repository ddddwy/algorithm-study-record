package undirectedGraph;

import edu.princeton.cs.algs4.Stack;

public class DepthFirstPaths {
	private boolean[] marked;
	private int[] edgeTo;
	private int s;
	
	public DepthFirstPaths(Graph G, int s){
		// initialize data structures
		int i = 0;
		for(int g: G.adj(s)){
			marked[i] = false;
			edgeTo[i] = -1;
			i++;
		}
		
		// find vertices connected to s
		dfs(G, s);
	}
	
	private void dfs(Graph G, int v){
		marked[v] = true;
		for(int w: G.adj(v)){
			if(!marked[w]){
				dfs(G, w);
				edgeTo[w] = v;
			}
		}
	}
	
	public boolean hasPathTo(int v){
		return marked[v];
	}
	
	public Iterable<Integer> pathTo(int v){
		if(!hasPathTo(v)) return null;
		Stack<Integer> path = new Stack<Integer>();
		for(int x=v; x!=s; x=edgeTo[x]){
			path.push(x);
		}
		path.push(s);
		return path;
	}

}
