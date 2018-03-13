package lesson2.labs.prob4;

import java.util.ArrayList;
import java.util.List;

public class NewMain {

	StudentSectionFactory factory = new StudentSectionFactory();
	Student bob = factory.createStudent("1", "Bob");
	Student tim = factory.createStudent("1", "Tim");
	Student allen = factory.createStudent("1", "Allen");
	Student[] students = { bob, tim, allen };
	Section bio1 = factory.createSection(1, "Biology");
	Section bio2 = factory.createSection(2, "Biology");
	Section math = factory.createSection(3, "Mathematics");

	public static void main(String[] args) {
		NewMain m = new NewMain();
		m.readDataFromDb();
		System.out.println(m.getTranscript(m.bob));
		System.out.println("Grades for math section:\n " + m.getGrades(m.math));
		System.out.println("Courses that Tim took: " + m.getCourseNames(m.tim));
		System.out.println("Students who got A's: " + m.getStudentsWith("A"));
	}

	private Transcript getTranscript(Student s) {
		return s.getTranscript();
	}

	private List<String> getCourseNames(Student s) {
		List<TranscriptEntry> all = s.grades;
		List<String> courseNames = new ArrayList<>();
		for (TranscriptEntry te : all) {
			courseNames.add(te.section.courseName);
		}
		return courseNames;
	}

	private List<String> getGrades(Section s) {
		List<String> grades = new ArrayList<>();
		for (TranscriptEntry t : s.gradeSheet) {
			grades.add(t.grade);
		}
		return grades;
	}

	private List<String> getStudentsWith(String grade) {
		List<String> studentNames = new ArrayList<>();
		for (Student s : students) {
			boolean found = false;
			for (TranscriptEntry te : s.grades) {
				if (!found) {
					if (te.grade.equals(grade)) {
						found = true;
						studentNames.add(s.name);
					}
				}
			}
		}
		return studentNames;
	}

	private void readDataFromDb() {
		factory.newTranscriptEntry(bob, bio1, "A");
		factory.newTranscriptEntry(bob, math, "B");
		factory.newTranscriptEntry(tim, bio1, "B+");
		factory.newTranscriptEntry(tim, math, "A-");
		factory.newTranscriptEntry(allen, math, "B");
		factory.newTranscriptEntry(allen, bio2, "B+");
	}
}
