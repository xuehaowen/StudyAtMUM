/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lesson3.labs.prob2;

/**
 *
 * @author 986358
 */
public class Apartment {

	private double rent;
	private Building building;

	Apartment(double rent, Building building) {
		this.rent = rent;
		this.building = building;
		this.building.getApartments().add(this);
	}
	
	public double getRent() {
		return rent;
	}

}
