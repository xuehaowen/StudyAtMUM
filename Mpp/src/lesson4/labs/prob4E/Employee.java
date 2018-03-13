package lesson4.labs.prob4E;

import java.util.ArrayList;
import java.util.List;

public class Employee {
	
	private String name;
	private List<Account> accts;
	
	public Employee(String name) {
		this.name = name;
		this.accts = new ArrayList<>();
	}
	public void addAccount(Account acct) {
		this.accts.add(acct);
	}
	public double computeUpdatedBalanceSum() {
		//implement
		double sum = 0;
		for(Account acct : accts) {
			sum += acct.computeUpdatedBalance();
		}
		return sum;
	}
	public String getName() {
		return name;
	}
}
