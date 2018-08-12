package pure.math;


public class Matrix {
	protected ComplexNumb [][] A;
	private int colums, rows;
	
	public Matrix() {
		super();
	}
	
	public Matrix(ComplexNumb [][] a) {
		super();
		A = a;
		rows=A.length;
		colums=A[0].length;
	}

	public ComplexNumb[][] getA() {
		return A;
	}

	public void setA(ComplexNumb[][] a) {
		A = a;
	}

	public int getColums() {
		return colums;
	}

	public void setColums(int colums) {
		this.colums = colums;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
	
	@Override
	public String toString() {
		String s="";
		for(int i=0;i<rows;i++) {
			s=s+"{";
			for(int j=0;j<colums;j++) {
				s=s+A[i][j];
				if(j<colums-1) s=s+", ";
			}
			s=s+"}\n";
		}
		return s;
	}
	
	@Override
	public Object clone() {
		Matrix A=new Matrix(this.A);
		return A;
	}
	
	
	
	protected void swapRows(int r1,int r2) throws IllegalArgumentException {
		if(r1>this.rows || r2>this.rows) throw new IllegalArgumentException("Invalid row numbers");
		ComplexNumb temp;
		if(r1!=r2) {
			for(int i=0;i<this.colums;i++) {
				temp=this.A[r1][i];
				this.A[r1][i]=this.A[r2][i];;
				this.A[r2][i]=temp;
			}
		}
	}
	
	/**
	 * Change matrix to echelon form
	 * @return rank of matrix
	 */
	public int echelonForm() throws ArithmeticException, CloneNotSupportedException{
		int rank=0;
		for(int c=0;c<this.colums;c++) {
			int flag=0;
			for(int r=rank;r<this.rows;r++) {
				if(flag==0) {
					if(!this.A[r][c].equals(0)) {
						flag=1;
						this.swapRows(rank, r);
						rank++;
					}					
				}
				else {
					if(!this.A[r][c].equals(0)) {
						for(int i=c;i<this.colums;i++) {
							this.A[r][i]=this.A[r][i].minus(this.A[rank-1][i].multiply(this.A[r][c].divide(this.A[rank-1][c])));
						}
					}
				}
			}
		
		}
		return rank;
	}
	
	public int rank() throws ArithmeticException, CloneNotSupportedException{
		Matrix A=(Matrix) this.clone();
		return A.echelonForm();
	}
	
	public Matrix multiply(Matrix B) throws IllegalArgumentException{
		if(this.colums!=B.rows) throw new IllegalArgumentException();
		ComplexNumb[][] X=new ComplexNumb[this.rows][B.colums];
		for(int r=0;r<this.rows;r++) {
			for(int c=0;c<B.colums;c++) {
				ComplexNumb a=new ComplexNumb(0);
				for(int i=0;i<this.colums;i++) {
					a=a.add(this.A[r][i].multiply(B.A[i][c]));
				}
				X[r][c]=a;
			}
		}
		Matrix C=new Matrix(X);
		return C;
	}

}
