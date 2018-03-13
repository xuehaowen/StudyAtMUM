package lesson4.labs.prob4C;

import java.time.LocalDate;

public class Main {

	public static void main(String[] args) {
		Hourly h1 = new Hourly(1, 20, 30);
		Hourly h2 = new Hourly(2, 15, 35);
		Salaried s1 = new Salaried(3, 3000);
		Salaried s2 = new Salaried(4, 4000);
		Commissioned c1 = new Commissioned(5, 0.1, 1000);
		Commissioned c2 = new Commissioned(6, 0.2, 2000);
		Employee[] emps = new Employee[] {h1,h2,s1,s2,c1,c2};
		Order o1 = new Order(1, LocalDate.of(2018, 1, 1), 100);
		Order o2 = new Order(2, LocalDate.of(2018, 2, 10), 200);
		Order o3 = new Order(3, LocalDate.of(2018, 2, 21), 200);
		Order o4 = new Order(4, LocalDate.of(2018, 3, 1), 150);
		c1.getOrders().add(o1);
		c1.getOrders().add(o2);
		c1.getOrders().add(o3);
		c2.getOrders().add(o4);
		for(Employee emp : emps) {
			System.out.printf("total salary is %.2f \n", emp.calcCompensation(1, 2018).getNetPay());
		}
	}
}
