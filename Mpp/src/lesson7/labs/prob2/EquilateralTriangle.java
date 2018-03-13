package lesson7.labs.prob2;

public class EquilateralTriangle implements Polygon {

	private double length;
	
	public EquilateralTriangle(double length) {
		this.length = length;
	}

	@Override
	public double[] getSides() {
		// TODO Auto-generated method stub
		return new double[] {length,length,length};
	}

}
