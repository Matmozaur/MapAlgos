package Graphs;

public abstract class algos {
	public static int min(int a, int b) {
		if(a<b) return a;
		return b;
	}
	public static int max(int a, int b) {
		if(a>b) return a;
		return b;
	}
	public static void sleep(long t) {
		try{
			Thread.sleep(t);
			}
			catch(InterruptedException e){
			}
	}
	
}
