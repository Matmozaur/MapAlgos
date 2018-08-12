package pure.math;

public class MatrixFunctions {

	public MatrixFunctions() {
		// TODO Auto-generated constructor stub
	}
	
	public static ComplexNumb lapleace(ComplexNumb [][] A,int dim) {
		if(dim==1) return A[0][0];
		else {
			ComplexNumb ret=new ComplexNumb(0);
			for(int i=0;i<dim;i++) {
				ret=ret.add(A[i][0].multiply(lapleace(MatrixFunctions.minor(A,i),dim-1).multiply(Math.pow(-1, i))));
			}
			return ret;
		}
	}
	
	public static ComplexNumb[][] minor(ComplexNumb [][] A,int i){
		ComplexNumb[][] B=new ComplexNumb[A.length-1][A.length-1];
		for(int c=1;c<A.length;c++) {
			for(int r=0;r<i;r++) {
				B[r][c-1]=A[r][c];
			}
			for(int r=i+1;r<A.length;r++) {
				B[r-1][c-1]=A[r][c];
			}
		}
		return B;
	}
	

}
