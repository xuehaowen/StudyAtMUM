package lesson2.labs.prob2A;

public class Student {

	private String name;
	private GradeReport report;

	public Student(String name) {
		this.name = name;
		this.report = new GradeReport(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GradeReport getReport() {
		return report;
	}

	public void setReport(GradeReport report) {
		this.report = report;
	}
}
