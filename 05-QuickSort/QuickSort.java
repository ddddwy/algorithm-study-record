package quickSort;

import edu.princeton.cs.algs4.StdRandom;

public class QuickSort {
	private static boolean less(Comparable a, Comparable b){
		return a.compareTo(b) < 0;
	}
	
	private static void exch(Comparable[] a, int i, int j){
		Comparable swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
	
	private static int partition(Comparable[] a, int low, int high){
		int i = low;
		int j = high;
		while(true){
			// find item on left to swap
			while(less(a[++i], a[low])){
				if(i == high) break;
			}
			
			// find item on right to swap
			while(less(a[low], a[--j])){
				if(j == low) break;
			}
			
			//check if pointers i and j crosses
			if(i >= j) break;
			exch(a, i, j);
		}
		
		// swap with partitioning item
		exch(a, low, j);
		return j;
	}
	
	public static void sort(Comparable[] a){
		// shuffle needed for performance guarantee
		StdRandom.shuffle(a);
		sort(a, 0, a.length-1);
	}
	
	public static void sort(Comparable[] a, int low, int high){
		if(high <= low) return ;
		int j = partition(a, low, high);
		sort(a, low, j-1);
		sort(a, j+1, high);
	}
	
	public static Comparable select(Comparable[] a, int k){
		// T(n) = O(n)
		StdRandom.shuffle(a);
		int low = 0;
		int high = a.length-1;
		while(high > low){
			int j = partition(a, low, high);
			if(j < k){
				low = j+1;
			}
			else if (j > k) {
				high = j-1;
			}else {
				return a[k];
			}
		}
		return a[k];
	}
	
	public static void sort_three_way(Comparable[] a, int low, int high){
		if(high <= low) return ; 
		int lt = low;
		int gt = high;
		Comparable v = a[low];
		int i = low;
		while(i <= gt){
			int cmp = a[i].compareTo(v);
			if(cmp < 0){
				exch(a, lt++, i++);
			}else if (cmp > 0) {
				exch(a, i, gt--);
			}else {
				i++;
			}
		}
		
		sort(a, low, lt-1);
		sort(a, gt+1, high);
	}
}
