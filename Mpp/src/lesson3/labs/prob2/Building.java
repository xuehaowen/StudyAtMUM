/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lesson3.labs.prob2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 986358
 */
public class Building {

	private Landlord owner;
	private List<Apartment> apartments;
	private double cost;

	Building(Landlord owner, double cost) {
		this.owner = owner;
		this.cost = cost;
		this.apartments = new ArrayList<Apartment>();
		this.owner.getBuildings().add(this);
	}

	public List<Apartment> getApartments() {
		return apartments;
	}
	
	public double getCost() {
		return cost;
	}

}
