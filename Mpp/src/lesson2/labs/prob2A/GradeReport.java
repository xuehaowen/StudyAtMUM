package lesson2.labs.prob2A;

public class GradeReport {

	private Student owner;

	GradeReport(Student student) {
		this.owner = student;
	}

	public Student getOwner() {
		return owner;
	}

	public void setOwner(Student owner) {
		this.owner = owner;
	}
}
