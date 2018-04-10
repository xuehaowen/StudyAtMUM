package edu.mum.cs.mpp.finalexam.part3q1.model;

public class CustomerAccountPair {
	
	public String customerName;
	public String accountNumber;
	
	public CustomerAccountPair(String n, String an) {
		customerName = n;
		accountNumber = an;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	
	public String toString() {
		return "(" + customerName + ", " + accountNumber + ")";
	}
}
