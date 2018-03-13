package lesson2.labs.prob4;

import java.util.*;

public class Section {
	public Section(int secNum, String courseName2) {
		this.sectionNumber = secNum;
		this.courseName = courseName2;
		this.gradeSheet = new ArrayList<TranscriptEntry>();
	}

	public Section() {
		// TODO Auto-generated constructor stub
	}

	String courseName;
	int sectionNumber;
	List<TranscriptEntry> gradeSheet;

}
