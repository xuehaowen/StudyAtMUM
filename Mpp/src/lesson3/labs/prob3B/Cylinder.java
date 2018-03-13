package lesson3.labs.prob3B;

public class Cylinder {

	private double radius;
	public double height;
	
	public Cylinder(double radius, double height) {
		this.radius = radius;
		this.height = height;
	}
	
	public double computeVolume() {
		return Math.pow(radius, 2) * Math.PI * height;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
}
