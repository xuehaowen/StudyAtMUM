package lesson3.labs.prob1;

public class PersonWithJob {

	private double salary;
	private Person person;

	public double getSalary() {
		return salary;
	}

	PersonWithJob(String n, double s) {
		person = new Person(n);
		salary = s;
	}

	@Override
	public boolean equals(Object aPerson) {
		if (aPerson == null)
			return false;
		if (aPerson instanceof Person) {
			Person p = (Person) aPerson;
			return p.equals(this.person);
		}
		if (!(aPerson instanceof PersonWithJob))
			return false;
		PersonWithJob p = (PersonWithJob) aPerson;
		boolean isEqual = this.person.getName().equals(p.person.getName()) && this.getSalary() == p.getSalary();
		return isEqual;
	}

	public static void main(String[] args) {
		PersonWithJob p1 = new PersonWithJob("Joe", 30000);
		Person p2 = new Person("Joe");
		PersonWithJob p3 = new PersonWithJob("Joe", 20000);
		// As PersonsWithJobs, p1 should be equal to p2
		// --------------------------------------------
		// Because p1 overrided the equals method inherited from p2,
		// so the result is different. To avoid this problem, we reconstructed
		// the structure of PersonWithJob with composition,
		// then modify the equal method in both class.
		// In this way, we make p1 equal to p2.
		System.out.println("p1.equals(p2)? " + p1.equals(p2));
		System.out.println("p2.equals(p1)? " + p2.equals(p1));
		// p1 is not equal to p3
		System.out.println("p1.equals(p3)? " + p1.equals(p3));
		System.out.println("p3.equals(p1)? " + p3.equals(p1));
	}

}
