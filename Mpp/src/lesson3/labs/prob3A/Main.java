package lesson3.labs.prob3A;

public class Main {

	public static void main(String[] args) {
		Cylinder cy = new Cylinder(2,2);
		System.out.printf("Cylinder's volume is %.2f\n", cy.computeVolume());
		Circle c = new Circle(2,2);
		//Circle do not have height.
		c.getHeight();
		//Circle do not have volume
		c.computeVolume();
		System.out.printf("Circle's area is %.2f", c.computeArea());
	}
}
