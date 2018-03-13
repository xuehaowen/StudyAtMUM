package lesson4.labs.prob4C;

public abstract class Employee {

	private int empId;
	
	public Employee(int empId) {
		this.empId = empId;
	}

	public void print() {
		
	}
	
	public PayCheck calcCompensation(int month, int year) {
		double grossPay = calcGrossPay(month, year);
		double fica = grossPay * Tax.FICA.getVal();
		double state = grossPay * Tax.StateTax.getVal();
		double local = grossPay * Tax.LocalTax.getVal();
		double medicare = grossPay * Tax.Medicare.getVal();
		double socialSecurity = grossPay * Tax.SocialSecurity.getVal();
		PayCheck pc = new PayCheck(grossPay, fica, state, local, medicare, socialSecurity);
		return pc;
	}
	
	public abstract double calcGrossPay(int month, int year);
}
