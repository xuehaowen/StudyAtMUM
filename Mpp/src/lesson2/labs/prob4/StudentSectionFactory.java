package lesson2.labs.prob4;

public class StudentSectionFactory {

	public Section createSection(int secNum, String courseName) {
		Section sect = new Section(secNum, courseName);
		return sect;

	}

	public Student createStudent(String id, String name) {
		Student s = new Student(id, name);
		return s;

	}

	public void newTranscriptEntry(Student s, Section sect, String grade) {
		TranscriptEntry te = new TranscriptEntry(s, sect, grade);
		s.grades.add(te);
		sect.gradeSheet.add(te);
	}
}
