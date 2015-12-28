package com.nixsolutions.entity;

public class Subject {
	private int subjectId;
	private String subjectName;
	private int termId;

	public Subject(int subjectId, String subjectName, int termId) {
		this.subjectId = subjectId;
		this.subjectName = subjectName;
		this.termId = termId;
	}

	public Subject() {
		this(0, "default", 0);
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public int getTermId() {
		return termId;
	}

	public void setTermId(int termId) {
		this.termId = termId;
	}
}
