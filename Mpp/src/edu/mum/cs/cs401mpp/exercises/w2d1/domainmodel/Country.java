package edu.mum.cs.cs401mpp.exercises.w2d1.domainmodel;

public final class Country {

	private final String countryName;
	private final CapitalCity capital;
	
	Country(String countryName, String capitalName) {
		this.countryName = countryName;
		this.capital = new CapitalCity(capitalName, this);
	}
	
	public String print() {
		StringBuilder sb = new StringBuilder("Country: ");
		sb.append(countryName).append("; ").append("Capital City: ").append(this.capital.getCityName());
		return sb.toString();
	}

}
