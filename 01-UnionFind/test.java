public class test{
  public static void main(String[] args){
    int N=9;
    WeightedQuickUnionUF quick_union=new WeightedQuickUnionUF(N);
    quick_union.union(1,2);
    boolean flag=quick_union.connected(1,2);
    System.out.println(flag);
  }
}