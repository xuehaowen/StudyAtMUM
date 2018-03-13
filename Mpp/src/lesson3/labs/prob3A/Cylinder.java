package lesson3.labs.prob3A;

public class Cylinder {

	public double radius;
	private double height;
	
	public Cylinder(double radius, double height) {
		this.radius = radius;
		this.height = height;
	}
	
	public double computeVolume() {
		return Math.pow(radius, 2) * Math.PI * height;
	}

	public double getHeight() {
		return height;
	}
	
}
