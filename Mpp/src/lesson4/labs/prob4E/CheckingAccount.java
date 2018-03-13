package lesson4.labs.prob4E;

public class CheckingAccount extends Account {

	private double monthlyFee;
	
	public CheckingAccount(String acctId, double fee, double startBalance) {
		super.acctId = acctId;
		super.balance = startBalance;
		this.monthlyFee = fee;
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
		return super.balance - this.monthlyFee;
	}

}
