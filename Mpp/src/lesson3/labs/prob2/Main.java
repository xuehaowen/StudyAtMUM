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
public class Main {

	public static void main(String args[]) {

		Landlord l = LandlordBuildingApartmentFactory.newLandlord();
		Building b1 = LandlordBuildingApartmentFactory.newBuilding(l, 100.00);
		Building b2 = LandlordBuildingApartmentFactory.newBuilding(l, 200.00);
		LandlordBuildingApartmentFactory.newApartment(50.00, b1);
		LandlordBuildingApartmentFactory.newApartment(60.00, b1);
		LandlordBuildingApartmentFactory.newApartment(70.00, b1);
		LandlordBuildingApartmentFactory.newApartment(25.00, b2);
		LandlordBuildingApartmentFactory.newApartment(45.00, b2);
		LandlordBuildingApartmentFactory.newApartment(65.00, b2);
		
		double cost = 0;
		double rent = 0;
		for(Building b : l.getBuildings()) {
			cost += b.getCost();
			for(Apartment a : b.getApartments()) {
				rent += a.getRent();
			}
		}
		System.out.printf("monthly totally profit is: %.2f", rent - cost);
	}
}
