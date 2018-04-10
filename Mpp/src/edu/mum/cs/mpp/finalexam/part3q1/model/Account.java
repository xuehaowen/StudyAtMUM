package edu.mum.cs.mpp.finalexam.part3q1.model;

import java.util.ArrayList;
import java.util.List;

public  class Account {
	
	private String accountNumber;
	private double balance;
	private List<Transaction> trxns = new ArrayList<>();

	public Account(String an){
		this.accountNumber = an;
	}

	public String getAccountNumber(){
		return this.accountNumber;
	}

	public double getBalance(){
		return this.balance;
	}
		
	public void setBalance(double balance) {
		this.balance = balance;
	}

	public List<Transaction> getTrxns() {
		return trxns;
	}

	public void addTransaction(Transaction trxn) {
		this.trxns.add(trxn);
	}

	public String toString(){
		return "Account:"+this.accountNumber + " with a balance of " + this.balance;
	}
}