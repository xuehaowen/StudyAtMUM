package lesson4.labs.prob4C;

public final class PayCheck {

	private double grossPay;
	private double fica;
	private double state;
	private double local;
	private double medicare;
	private double socialSecurity;
	
	public PayCheck(double grossPay, double fica, double state, double local, double medicare, double socialSecurity) {
		this.grossPay = grossPay;
		this.fica = fica;
		this.state = state;
		this.local = local;
		this.medicare = medicare;
		this.socialSecurity = socialSecurity;
	}
	
	public void print() {
		
	}
	
	public double getNetPay() {
		return grossPay - (fica + state + local + medicare + socialSecurity);
	}
}
