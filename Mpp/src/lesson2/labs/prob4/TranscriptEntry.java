package lesson2.labs.prob4;

class TranscriptEntry {
	Student student;
	Section section;
	String grade;

	public TranscriptEntry(Student s, Section sect, String grade2) {
		this.student = s;
		this.section = sect;
		this.grade = grade2;
	}

	public TranscriptEntry() {
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		return "Student: " + student.name + "\n" + "Course name: " + section.courseName + "\n" + "Section number: "
				+ section.sectionNumber + "\n" + "Grade: " + grade + "\n";
	}
}
