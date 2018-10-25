package MergeSort;

public class Merge {
	private static boolean less(Comparable v, Comparable w){
		return v.compareTo(w) < 0;
	}
	
	private static boolean isSorted(Comparable[] a, int low, int high){
		for(int i=low; i<=high; i++){
			if(less(a[i], a[i-1])){
				return false;
			}
		}
		return true;
	}
	
	private static void merge(Comparable[] a, Comparable[] aux, int low, int mid, int high){
		assert isSorted(a, low, mid);		// precondition: a[low, ..., mid] is sorted
		assert isSorted(a, mid+1, high);	// precondition: a[mid+1, ..., high] is sorted
		
		for(int k=low; k<=high; k++){
			aux[k] = a[k];
		}
		
		int i=low;
		int j=mid+1;
		for(int k=low; k<=high; k++){
			if(i > mid){
				a[k] = aux[j++];
			}else if (j > high) {
				a[k] = aux[i++];
			}else if (less(aux[j], aux[i])) {
				a[k] = aux[j++];
			}else {
				a[k] = aux[i++];
			}
		}
		
		assert isSorted(a, low, high);
	}
	
	private static void sort(Comparable[] a, Comparable[] aux, int low, int high) {
		if(high <= low){
			return;
		}
		int mid = low + (high-low)/2;
		sort(a, aux, low, mid);
		sort(a, aux, mid+1, high);
		merge(a, aux, low, mid, high);
	}
	
	public static void sort(Comparable[] a){
		Comparable[] aux = new Comparable[a.length];
		sort(a, aux, 0, a.length-1);
	}

}
