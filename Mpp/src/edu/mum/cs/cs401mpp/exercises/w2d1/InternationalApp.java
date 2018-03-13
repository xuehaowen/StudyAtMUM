package edu.mum.cs.cs401mpp.exercises.w2d1;

import edu.mum.cs.cs401mpp.exercises.w2d1.domainmodel.Country;
import edu.mum.cs.cs401mpp.exercises.w2d1.domainmodel.CountryCapitalCiryFactory;

public class InternationalApp {

	public static void main(String[] args) {
		Country[] countries = new Country[] {
				CountryCapitalCiryFactory.getInstance().newCountry("United States", "Washington, DC"),
				CountryCapitalCiryFactory.getInstance().newCountry("Ireland", "Dublin"),
				CountryCapitalCiryFactory.getInstance().newCountry("India", "New Delhi")
		};
		
		for(Country country : countries) {
			System.out.println(country.print());
		}
	}

}
