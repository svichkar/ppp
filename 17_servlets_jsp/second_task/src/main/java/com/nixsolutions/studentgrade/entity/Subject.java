package com.nixsolutions.studentgrade.entity;

public class Subject {
	private Long subjectId;
	private String subjectName;
	private Long termId;

	public Subject(Long subjectId, String subjectName, Long termId) {
		this.subjectId = subjectId;
		this.subjectName = subjectName;
		this.termId = termId;
	}

	public Subject() {
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Long getTermId() {
		return termId;
	}

	public void setTermId(Long termId) {
		this.termId = termId;
	}
}
