import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	private Percolation pr;
	private double[] ps;
	private int T;
	
	// perform trials independent experiments on an n-by-n grid
	public PercolationStats(int n, int trials){
		if (n<=0 || trials<=0) {
			throw new IllegalArgumentException();
		}else{
			T=trials;
			ps=new double[trials];
			for(int i=0;i<trials;i++){
				pr=new Percolation(n);
				while(!pr.percolates()){
					int row=StdRandom.uniform(1, n+1);
					int col=StdRandom.uniform(1, n+1);
					pr.open(row, col);
				}
				double nums=pr.numberOfOpenSites();
				double p=nums/(n*n);
				ps[i]=p;
			}
		}
	}
	
	// sample mean of percolation threshold
	public double mean(){
		return StdStats.mean(ps);
	}
	
	// sample standard deviation of percolation threshold
	public double stddev(){
		return StdStats.stddev(ps);
	}
	
	// low  endpoint of 95% confidence interval
	public double confidenceLo() {
		double mean=mean();
		double std=stddev();
		double out=mean-1.96*std/Math.sqrt(T);
		return out;
	}
	
	// high endpoint of 95% confidence interval
	public double confidenceHi(){
		double mean=mean();
		double std=stddev();
		double out=mean+1.96*std/Math.sqrt(T);
		return out;
	}
	
	public static void main(String[] args) {
		if(args.length!=2){
			throw new IllegalArgumentException();
		}else{
			int n=Integer.parseInt(args[0]);
			int trials=Integer.parseInt(args[1]);
			PercolationStats psStats=new PercolationStats(n, trials);
			System.out.println("mean                    = "+psStats.mean());
			System.out.println("stddev                  = "+psStats.stddev());
			System.out.println("95% confidence interval = ["+psStats.confidenceLo()+", "+psStats.confidenceHi()+"]");
		}
	}
}
