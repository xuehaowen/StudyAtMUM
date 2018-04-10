package edu.mum.cs.mpp.finalexam.part3q1.model;

public class Customer {
	
	String name;
	Address address;
	Account account;
	
	public Customer(String n, Address a, Account ac) {
		this.name = n;
		this.address = a;
		this.account = ac;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("<");
		sb.append("name: ");
		sb.append(name+">");
		return sb.toString();
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public Account getAccount() {
		return account;
	}
	
}

