package lesson5.labs.prob2;

public final class Rectangle implements Shape {

	private double height;
	private double width;

	public Rectangle(double height, double width) {
		this.height = height;
		this.width = width;
	}

	@Override
	public double computeArea() {
		return height * width;
	}

	public double getHeight() {
		return height;
	}

	public double getWidth() {
		return width;
	}

}
