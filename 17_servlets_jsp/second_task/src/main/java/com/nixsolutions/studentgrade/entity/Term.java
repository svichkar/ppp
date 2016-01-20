package com.nixsolutions.studentgrade.entity;

public class Term {
	private Long termId;
	private String termName;

	public Term(Long termId, String termName) {
		this.termId = termId;
		this.termName = termName;
	}

	public Term() {
	}

	public Long getTermId() {
		return termId;
	}

	public void setTermId(Long termId) {
		this.termId = termId;
	}

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

}
