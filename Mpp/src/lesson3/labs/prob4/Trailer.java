package lesson3.labs.prob4;

public class Trailer extends Building{

	public Trailer() {
		this("Trailer Park");
	}
	
	public Trailer(String address) {
		super(address);
	}

	@Override
	public double computeRent() {
		rent = 500;
		return rent;
	}

}
