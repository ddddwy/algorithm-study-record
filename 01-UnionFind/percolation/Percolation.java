import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private boolean[][] grid;
	private int count=0;
	private WeightedQuickUnionUF wuf;
	private int size;
	private int top;
	private int bottom;
	
	// create n-by-n grid, with all sites blocked
	public Percolation(int n){
		if(n<=0){
			throw new IllegalArgumentException();
		}else{
			size=n;
			grid=new boolean[n][n];
			top=0;
			bottom=n*n+1;
			wuf=new WeightedQuickUnionUF(n*n+2);
			for(int i=0;i<n;i++){
				for(int j=0;j<n;j++){
					grid[i][j]=false;
				}
			}
		}
	}
	
	// get the index of (row, col) for weighted quick union 
	private int getIndex(int row, int col){
		return (row-1)*size+col;
	}
	
	// open site (row, col) if it is not open already
	public void open(int row, int col){
		if(row<=0 || col<=0 || row>size || col>size){
			throw new IllegalArgumentException();
		}else{
			if(!grid[row-1][col-1]){
				grid[row-1][col-1]=true;
				count++;
			}
			
			if(row==1){
				wuf.union(getIndex(row, col), top);
			}
			if(row==size){
				wuf.union(getIndex(row, col), bottom);
			}
			if(col>1 && isOpen(row, col-1)){
				wuf.union(getIndex(row, col), getIndex(row, col-1));
			}
			if(col<size && isOpen(row, col+1)){
				wuf.union(getIndex(row, col), getIndex(row, col+1));
			}
			if(row>1 && isOpen(row-1, col)){
				wuf.union(getIndex(row, col), getIndex(row-1, col));
			}
			if(row<size && isOpen(row+1, col)){
				wuf.union(getIndex(row, col), getIndex(row+1, col));
			}
		}
		
	}
	
	// is site (row, col) open?
	public boolean isOpen(int row, int col){
		if(row<=0 || col<=0 || row>size || col>size){
			throw new IllegalArgumentException();
		}
		return grid[row-1][col-1];
	}
	
	// is site (row, col) full?
	/** A full site is an open site that can be 
	 connected to an open site in the top row 
	 via a chain of neighboring open sites. **/
	public boolean isFull(int row, int col){
		boolean flag=false;
		if(1<=row && row<=size && 1<=col && col<=size){
			if(wuf.connected(getIndex(row, col), top)) flag=true;
		}else {
			throw new IllegalArgumentException();
		}
		return flag;
	}
	
	// number of open sites
	public int numberOfOpenSites(){
		return count;
	}
	
	// does the system percolate?
	/** the system percolates if there is 
	 * a full site in the bottom row. **/
	public boolean percolates(){
		return wuf.connected(bottom, top);
	}
	
	public static void main(String[] args){
		int n=20;
		Percolation percolation=new Percolation(n);
		while(!percolation.percolates()){
			int row=StdRandom.uniform(1, n+1);
			int col=StdRandom.uniform(1, n+1);
			String x='<'+Integer.toString(row)+','+Integer.toString(row)+'>';
			System.out.println(x);
			percolation.open(row, col);
		}
		double nums=percolation.numberOfOpenSites();
		double p=nums/(n*n);
		System.out.println(p);
	}

}
