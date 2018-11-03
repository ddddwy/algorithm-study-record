package undirectedGraph;

import edu.princeton.cs.algs4.Queue;

public class BreadthFirstPaths {
	private boolean[] marked;
	private int[] edgeTo;
	
	public BreadthFirstPaths(Graph G, int s){
		// initialize data structures
		int i = 0;
		for(int g: G.adj(s)){
			marked[i] = false;
			edgeTo[i] = -1;
			i++;
		}
				
		// find vertices connected to s
		bfs(G, s);
	}
	
	private void bfs(Graph G, int s){
		Queue<Integer> q = new Queue<Integer>();
		q.enqueue(s);
		marked[s] = true;
		while(!q.isEmpty()){
			int v = q.dequeue();
			for(int w: G.adj(v)){
				if(!marked[w]){
					marked[w] = true;
					edgeTo[w] = v;
					q.enqueue(w);
				}
			}
		}
	}

}
