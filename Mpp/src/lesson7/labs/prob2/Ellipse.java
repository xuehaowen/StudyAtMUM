package lesson7.labs.prob2;

public class Ellipse implements ClosedCurve {

	private double a;
	private double E;
	public Ellipse(double a, double e) {
		this.a = a;
		E = e;
	}
	@Override
	public double computePerimeter() {
		// TODO Auto-generated method stub
		return 4*E*a;
	}

}
