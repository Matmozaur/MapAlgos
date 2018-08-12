package pure.math;

import static java.lang.Math.PI;

import java.awt.print.Printable;

public class ComplexNumb implements Cloneable{
	private double re,im;

	public ComplexNumb(double a, double b) {
		super();
		this.re = a;
		this.im = b;
	}

	public ComplexNumb(double a) {
		super();
		this.re = a;
		this.im = 0;
	}

	public double getRe() {
		return re;
	}

	public void setRe(double a) {
		this.re = a;
	}

	public double getIm() {
		return im;
	}

	public void setIm(double b) {
		this.im = b;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}
	
	@Override
	public String toString(){
		String s=""+this.getRe();
		if(this.getIm()!=0) s=s+" + "+this.getIm()+"i";
		return s;
	}
	
	
	public boolean equals(double a) {
		if(this.re==a && this.im==0) return true;
		else return false;
	}
	
	public boolean equals(ComplexNumb a) {
		if(this.re==a.re && this.im==a.im) return true;
		else return false;
	}
	
	public double abs() {
		return Math.sqrt(Math.pow(this.re,2)+Math.pow(this.im,2));
	}
	
	public ComplexNumb conjugate() {
		return new ComplexNumb(this.re,-this.im);
	}
	
	public double argument() {
		if(this.re!=0) {
			if(this.re>0) return Math.atan(this.im/this.re);
			else return Math.atan(this.im/this.re) + PI;
		}
		else {
			if(this.im>=0) return PI/2;
			else return -PI/2;
		}
	}
	
	public ComplexNumb add(double x) {
		return new ComplexNumb(this.re+x,this.im);
	}
	
	public ComplexNumb minus(double x) {
		return new ComplexNumb(this.re-x,this.im);
	}
	
	public ComplexNumb multiply(double x) {
		return new ComplexNumb(this.re*x,this.im*x);
	}
	
	public ComplexNumb divide(double x) throws ArithmeticException {
		return new ComplexNumb(this.re/x,this.im/x);
	}
	
	public ComplexNumb add(ComplexNumb x) {
		return new ComplexNumb(this.re+x.re,this.im+x.im);
	}
	
	public ComplexNumb minus(ComplexNumb x) {
		return new ComplexNumb(this.re-x.re,this.im-x.im);
	}
	
	public ComplexNumb multiply(ComplexNumb x) {
		return new ComplexNumb(this.re*x.re-this.im*x.im,this.re*x.im+this.im*x.re);
	}
	
	public ComplexNumb divide(ComplexNumb x) throws ArithmeticException, CloneNotSupportedException {
		double d=x.re*x.re+x.im*x.im;
	    ComplexNumb z= (ComplexNumb) this.clone();
	    z=z.multiply(x.conjugate());
	    z=z.divide(d);
	    return z;
	}
	

}
