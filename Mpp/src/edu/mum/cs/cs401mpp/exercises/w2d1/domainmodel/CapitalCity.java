package edu.mum.cs.cs401mpp.exercises.w2d1.domainmodel;

public final class CapitalCity {

	private final String cityName;
	private final Country country;
	
	CapitalCity(String cityName, Country country) {
		this.cityName = cityName;
		this.country = country;
	}

	public String getCityName() {
		return cityName;
	}

	public Country getCountry() {
		return country;
	}

	
}
