package pure.math;

public class MainTests {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ComplexNumb x=new ComplexNumb(3,4);
		ComplexNumb y=new ComplexNumb(1,1);
		try {
			System.out.println(x.divide(y).toString());
		}
		catch(ArithmeticException e) {
			e.printStackTrace();
		}
		catch(CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		ComplexNumb [][] a=new ComplexNumb [3][2];
		a[0][0]=new ComplexNumb(1);
		a[0][1]=new ComplexNumb(2);
		a[1][0]=new ComplexNumb(1);
		a[1][1]=new ComplexNumb(2);
		a[2][0]=new ComplexNumb(1);
		a[2][1]=new ComplexNumb(2);
		ComplexNumb [][] b= {{x,y},{x,x}};
		Matrix A=new Matrix(a);
		System.out.println(A.toString());
		int r=-1;
		try{
			r=A.echelonForm();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println(A.toString()+"rank="+r);
		
		
		ComplexNumb [][] c=new ComplexNumb [2][2];
		c[0][0]=new ComplexNumb(1);
		c[0][1]=new ComplexNumb(2);
		c[1][0]=new ComplexNumb(2);
		c[1][1]=new ComplexNumb(2);
		SquareMatrix B=new SquareMatrix(b);
		SquareMatrix C=new SquareMatrix(c);
		System.out.println("\n \n \n");
		System.out.println(B);
		System.out.println(B.determinant());
		
		ComplexNumb [][] d=new ComplexNumb [2][1];
		d[0][0]=new ComplexNumb(1);
		d[1][0]=new ComplexNumb(1);
		Matrix D=new Matrix(d);
		System.out.println(B.multiply(C));
		System.out.println(B.multiply(D));
	}

}
