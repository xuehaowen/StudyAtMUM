package lesson4.labs.prob4C;

public enum Tax {

	FICA(0.23), StateTax(0.05), LocalTax(0.01), Medicare(0.03), SocialSecurity(0.075);
	
	private double val;
	
	private Tax(double val) {
		this.val = val;
	}
	
	public double getVal() {
		return this.val;
	}
}
