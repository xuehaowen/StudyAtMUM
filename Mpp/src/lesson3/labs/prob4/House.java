package lesson3.labs.prob4;

public class House extends Building{

	private double lotSize;
	
	public House(String address) {
		super(address);
	}

	public House(int lotSize) {
		this("");
		this.lotSize = lotSize;
	}
	
	public House(int lotSize, String address) {
		this(address);
		this.lotSize = lotSize;
	}

	@Override
	public double computeRent() {
		rent = 0.1 * lotSize;
		return rent;
	}

}
