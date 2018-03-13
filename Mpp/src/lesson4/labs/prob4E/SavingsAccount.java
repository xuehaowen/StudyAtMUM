package lesson4.labs.prob4E;

public class SavingsAccount extends Account {

	private double interestRate;
	
	public SavingsAccount(String acctId, double interestRate, double startBalance) {
		super.acctId = acctId;
		super.balance = startBalance;
		this.interestRate = interestRate;
	}
	
	@Override
	public String getAccountId() {
		// TODO Auto-generated method stub
		return super.acctId;
	}

	@Override
	public double getBalance() {
		// TODO Auto-generated method stub
		return super.balance;
	}

	@Override
	public double computeUpdatedBalance() {
		// TODO Auto-generated method stub
		return super.balance + (super.balance * this.interestRate);
	}

}
