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
public class LandlordBuildingApartmentFactory {

	public static Landlord newLandlord() {
		Landlord l = new Landlord();
		return l;
	}

	public static Building newBuilding(Landlord owner, double cost) {
		Building b = new Building(owner, cost);
		return b;
	}

	public static void newApartment(double rent, Building building) {
		new Apartment(rent, building);
	}
}
