package lesson5.labs.prob3;

import java.time.LocalDate;

public class OrderFactory {

	private static OrderFactory instance;

	public static OrderFactory getIntance() {
		if (instance == null) {
			synchronized (OrderFactory.class) {
				instance = new OrderFactory();
			}
		}
		return instance;
	}

	public Order newOrder(Customer cust, LocalDate date) {
		if (cust == null)
			throw new NullPointerException("Null customer");
		Order ord = new Order(date);
		cust.addOrder(ord);
		return ord;
	}

	public Customer newCustomer(String name) {
		return new Customer(name);
	}

	public Item newItem(String name) {
		return new Item(name);
	}
}
