package addicional;

public abstract class algos {
	public static int min(int a, int b) {
		if(a<b) return a;
		return b;
	}
	public static int max(int a, int b) {
		if(a>b) return a;
		return b;
	}
	/**
	 * stops program for t milisec
	 * @param t
	 * @author Matmozaur
	 */
	public static void sleep(long t) {
		try{
			Thread.sleep(t);
			}
			catch(InterruptedException e){
			}
	}
	public static int max(int D[],int n) {
		int m=D[0];
		for(int i=1;i<n;i++) if(D[i]>m) m=D[i];
		return m;
	}
	public static int min(int D[],int n) {
		int m=D[0];
		for(int i=1;i<n;i++) if(D[i]<m) m=D[i];
		return m;
	}
}
