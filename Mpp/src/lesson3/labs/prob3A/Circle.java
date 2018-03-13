package lesson3.labs.prob3A;

public class Circle extends Cylinder{

	public Circle(double radius, double height) {
		super(radius, height);
	}

	public double computeArea() {
		return Math.pow(super.radius, 2) * Math.PI;
	}
	
	public double getRadius() {
		return super.radius;
	}
}
