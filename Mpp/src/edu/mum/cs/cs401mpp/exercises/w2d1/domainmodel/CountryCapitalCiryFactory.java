package edu.mum.cs.cs401mpp.exercises.w2d1.domainmodel;

public class CountryCapitalCiryFactory {

	private static CountryCapitalCiryFactory instance;
	
	public static CountryCapitalCiryFactory getInstance() {
		if(instance == null) {
			synchronized (CountryCapitalCiryFactory.class) {
				instance = new CountryCapitalCiryFactory();
			}
		}
		return instance;
	}
	
	public Country newCountry(String countryName, String capitalName) {
		return new Country(countryName, capitalName);
	}
}
