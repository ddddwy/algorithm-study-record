package MergeSort;

public class MergeBU {
	private static boolean less(Comparable v, Comparable w){
		return v.compareTo(w) < 0;
	}
	
	private static boolean isSorted(Comparable[] a, int low, int high){
		for(int i=low; i<=high; i++){
			if(less(a[i], a[i-1])) return false;
		}
		return true;
	}
	
	private static void merge(Comparable[] a, Comparable[] aux, int low, int mid, int high){
		assert isSorted(a, low, mid);
		assert isSorted(a, mid+1, high);
		
		for(int k=0; k<=a.length; k++){
			aux[k] = a[k];
		}
		
		int i = low;
		int j = mid+1;
		for(int k=low; k<=high; k++){
			if(i > mid){
				a[k] = aux[j++];
			}else if (j > high) {
				a[k] = aux[i++];
			}else if (less(a[j], a[i])) {
				a[k] = aux[j++];
			}else {
				a[k] = aux[i++];
			}
		}
	}
	
	public static void sort(Comparable[] a){
		int N = a.length;
		Comparable[] aux = new Comparable[N];
		for(int sz=1; sz<N; sz+=sz){
			for(int low=0; low<N-sz; low+=sz+sz){
				merge(a, aux, low, low+sz-1, Math.min(low+sz+sz-1, N-1));
			}
		}
	}

}
