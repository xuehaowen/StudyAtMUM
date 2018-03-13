package lesson3.labs.prob4;

public class Admin {
	public static double computeTotalRent(Building[] properties) {
		double totalRent = 0;
		for (Building b : properties) {
			totalRent += b.computeRent();
		}
		return totalRent;
	}
}
