package lesson3.labs.prob4;

public abstract class Building {

	public double rent;
	private String address;
	public Building(String address) {
		this.address = address;
	}
	
	public abstract double computeRent();

	public String getAddress() {
		return address;
	}
}
