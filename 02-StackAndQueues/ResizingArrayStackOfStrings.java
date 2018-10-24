package alg4;

public class ResizingArrayStackOfStrings {
	private String[] s;
	private int N = 0;
	
	public ResizingArrayStackOfStrings(){
		s = new String[1];
	}
	
	public void push(String item){
		// double size of array s[] when array is full
		if (N == s.length){
			resize(2*s.length);
		}
		s[N++] = item;
	}
	
	public void resize(int capacity){
		String[] copy = new String[capacity];
		for (int i=0; i<N; i++){
			copy[i] = s[i];
		}
		s = copy;
	}
	
	public boolean isEmpty(){
		return N==0;
	}
		
	public String pop(){
		// halve size of array s[] when array is one-quarter full
		String item = s[--N];
		s[N] = null;
		if (N > 0 && N == s.length/4){
			resize(s.length/2);
		}
		return item;
	}

}
