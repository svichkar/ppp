package com.nixsolutions.entities;

public class Journal extends BaseEntity {
	private int journalId;
	private int studentId;
	private int subjectId;
	private int rate;
	
	public Journal(int journalId, int studentId, int subjectId, int rate){
		this.journalId = journalId;
		this.setStudentId(studentId);
		this.setSubjectId(subjectId);
		this.setRate(rate);
	}

	@Override
	public int getId() {
		return journalId;
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

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}	
}
