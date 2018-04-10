package lesson7.inclassexercises.exercise_2;

public class Main {

	public static void main(String[] args) {
		printSalesData(Constants.COMPANY, Constants.SALES_TARGET);
		System.out.println();
		printSalesData("Google", 550000000.00);
	}
	
	private static void printSalesData(String company, double sales_target) {
		System.out.printf("Company: %s\nSales Target: %.2f", company, sales_target);
	}

}
