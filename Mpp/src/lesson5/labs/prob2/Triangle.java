package lesson5.labs.prob2;

public final class Triangle implements Shape {

	private double base;
	private double height;

	public Triangle(double base, double height) {
		this.base = base;
		this.height = height;
	}

	@Override
	public double computeArea() {
		return base * height / 2;
	}

	public double getBase() {
		return base;
	}

	public double getHeight() {
		return height;
	}

}
