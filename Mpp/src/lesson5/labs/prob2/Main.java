package lesson5.labs.prob2;

public class Main {

	public static void main(String[] args) {
		Shape[] shapes = new Shape[] {
				new Circle(2), new Triangle(1.5, 3), new Rectangle(2, 2)
		};
		double sumArea = 0;
		for(Shape s : shapes) {
			sumArea += s.computeArea();
		}
		System.out.printf("Sum of area = %.2f", sumArea);
	}

}
