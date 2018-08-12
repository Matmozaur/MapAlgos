package pure.math;

public class SquareMatrix extends Matrix {

	public SquareMatrix() {
		// TODO Auto-generated constructor stub
	}

	public SquareMatrix(ComplexNumb[][] a) throws NotSquareException{
		super(a);
		if(super.getColums()!=super.getRows()) throw new NotSquareException();
		// TODO Auto-generated constructor stub
	} 
	
	public ComplexNumb determinant() {
		return MatrixFunctions.lapleace(this.A, this.getColums());
	}
	

}
