package lesson4.labs.prob4E;

public abstract class Account {

	public double balance;
	public String acctId;
	
	public abstract String getAccountId();
	public abstract double getBalance();
	public abstract double computeUpdatedBalance();
}
