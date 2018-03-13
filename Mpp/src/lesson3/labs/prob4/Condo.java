package lesson3.labs.prob4;

public class Condo extends Building{

	private int numFloors;
	
	public Condo(String address) {
		super(address);
	}

	public Condo(int numFloors) {
		this("");
		this.numFloors = numFloors;
	}
	
	public Condo(int numFloors, String address) {
		this(address);
		this.numFloors = numFloors;
	}

	@Override
	public double computeRent() {
		rent = 400 * numFloors;
		return rent;
	}

}
