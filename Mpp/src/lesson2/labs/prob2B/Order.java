package lesson2.labs.prob2B;

import java.util.List;

public class Order {

	private int OrderNum;
	private List<OrderLine> orderLines;

	public Order(int OrderNum) {
		if (OrderNum < 0)
			OrderNum = 1;
		this.OrderNum = OrderNum;
		for (int i = 0; i < OrderNum; i++) {
			OrderLine line = new OrderLine(this);
			orderLines.add(line);
		}
	}

	public int getOrderNum() {
		return OrderNum;
	}

	public void setOrderNum(int orderNum) {
		OrderNum = orderNum;
	}

	public List<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}
}
