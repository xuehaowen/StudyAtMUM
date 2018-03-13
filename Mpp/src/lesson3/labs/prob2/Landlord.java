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
public class Landlord {

	private List<Building> buildings;

	Landlord() {
		this.buildings = new ArrayList<Building>();
	}

	public List<Building> getBuildings() {
		return buildings;
	}
}
