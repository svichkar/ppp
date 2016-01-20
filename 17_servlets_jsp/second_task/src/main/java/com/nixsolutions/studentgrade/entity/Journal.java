package com.nixsolutions.studentgrade.entity;

public class Journal {
	private Long journalId;
	private Long studentId;
	private Long subjectId;
	private int gradeId;

	public Journal(Long journalId, Long studentId, Long subjectId, int gradeId) {
		this.journalId = journalId;
		this.studentId = studentId;
		this.subjectId = subjectId;
		this.gradeId = gradeId;
	}

	public Journal() {
	}

	public Long getJournalId() {
		return journalId;
	}

	public void setJournalId(Long journalId) {
		this.journalId = journalId;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public int getGradeId() {
		return gradeId;
	}

	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}
}
