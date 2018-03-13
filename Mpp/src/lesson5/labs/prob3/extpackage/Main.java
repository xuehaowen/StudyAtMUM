package lesson5.labs.prob3.extpackage;

import java.time.LocalDate;

import lesson5.labs.prob3.Customer;
import lesson5.labs.prob3.Order;
import lesson5.labs.prob3.OrderFactory;

public class Main {
	public static void main(String[] args) {
		Customer cust = OrderFactory.getIntance().newCustomer("Bob");
		Order order = OrderFactory.getIntance().newOrder(cust, LocalDate.now());
		order.addItem(OrderFactory.getIntance().newItem("Shirt"));
		order.addItem(OrderFactory.getIntance().newItem("Laptop"));

		order = OrderFactory.getIntance().newOrder(cust, LocalDate.now());
		order.addItem(OrderFactory.getIntance().newItem("Pants"));
		order.addItem(OrderFactory.getIntance().newItem("Knife set"));

		System.out.println(cust.getOrders());
	}
}

		
