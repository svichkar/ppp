package com.nixsolutions.studentgrade.entity;

public class Term {
	private int termId;
	private String termName;

	public Term(int termId, String termName) {
		this.termId = termId;
		this.termName = termName;
	}

	public Term() {
		this(0, "default");
	}

	public int getTermId() {
		return termId;
	}

	public void setTermId(int termId) {
		this.termId = termId;
	}

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

}
