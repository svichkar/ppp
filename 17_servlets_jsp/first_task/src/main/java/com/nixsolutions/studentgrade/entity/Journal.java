package com.nixsolutions.studentgrade.entity;

public class Journal {
	private int journalId;
	private int studentId;
	private int subjectId;
	private int gradeId;

	public Journal(int journalId, int studentId, int subjectId, int gradeId) {
		this.journalId = journalId;
		this.studentId = studentId;
		this.subjectId = subjectId;
		this.gradeId = gradeId;
	}

	public Journal() {
		this(0, 0, 0, 0);
	}

	public int getJournalId() {
		return journalId;
	}

	public void setJournalId(int journalId) {
		this.journalId = journalId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getGradeId() {
		return gradeId;
	}

	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}
}
