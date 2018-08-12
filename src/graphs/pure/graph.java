package graphs.pure;

public abstract class graph {
	public final static int n=30;
	int V;
	public int getV() {
		return V;
	}
	public void setV(int v) {
		V = v;
	}
	public graph(int v){
		V=v;
	}
	public static void swap(int A[],int B[]){
		int C[]=A;
		A=B;
		B=C;
	}
}
