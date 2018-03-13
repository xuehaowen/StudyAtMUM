package lesson3.labs.prob3B;

public class Circle {

	private Cylinder cylinder;
	
	public Circle(double radius) {
		cylinder = new Cylinder(radius, 0);
	}
	public double computeArea() {
		return Math.pow(cylinder.getRadius(), 2) * Math.PI;
	}
	
}
