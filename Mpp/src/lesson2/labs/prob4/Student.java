package lesson2.labs.prob4;

import java.util.*;

public class Student {
	String name;
	List<TranscriptEntry> grades;

	public Student(String id, String name2) {
		this.name = name2;
		this.grades = new ArrayList<TranscriptEntry>();
	}

	public Student() {
		// TODO Auto-generated constructor stub
	}

	public Transcript getTranscript() {
		return new Transcript(grades, this);

	}

}
