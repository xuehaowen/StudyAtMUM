package lesson4.labs.prob4C;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Commissioned extends Employee {

	private double commission;
	private double baseSalary;
	private List<Order> orders;
	
	public Commissioned(int empId, double commission, double baseSalary) {
		super(empId);
		this.commission = commission;
		this.baseSalary = baseSalary;
		orders = new ArrayList<>();
	}

	@Override
	public double calcGrossPay(int month, int year) {
		double total = 0;
		for(Order order : orders) {
			LocalDate local = order.getOrderDate().plusMonths(1);
			if(local.getYear() == year && local.getMonthValue() == month) {
				total += order.getOrderAmount();
			}
		}
		return baseSalary + total * commission;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
