package com.nixsolutions.studentgrade.bean;

import com.nixsolutions.studentgrade.entity.Subject;
import com.nixsolutions.studentgrade.entity.Term;

public class SubjectBean {
	private Long subjectId;
	private String subjectName;
	private String termName;

	public SubjectBean() {

	}

	public SubjectBean(Subject subject, Term term) {
		subjectId = subject.getSubjectId();
		subjectName = subject.getSubjectName();
		termName = term.getTermName();

	}

	public Long getSubjectId() {
		return subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public String getTermName() {
		return termName;
	}

}
