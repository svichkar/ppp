package com.nixsolutions.studentgrade.bean;

import com.nixsolutions.studentgrade.entity.Grade;
import com.nixsolutions.studentgrade.entity.Journal;
import com.nixsolutions.studentgrade.entity.Subject;
import com.nixsolutions.studentgrade.entity.Term;

public class JournalBean {
	private Long journalId;
	private String studentNameInGroup;
	private String subjectName;
	private String gradeName;
	private String termName;
	private String gpaGradeName;

	public JournalBean() {
	}

	public JournalBean(Journal journal, StudentBean student, Subject subject, Grade grade) {
		journalId = journal.getJournalId();
		studentNameInGroup = student.getStudentNameInGroup();
		subjectName = subject.getSubjectName();
		gradeName = grade.getGradeName();
	}

	public JournalBean(Term term, Subject subject, Grade grade, Grade gpa) {
		termName = term.getTermName();
		subjectName = subject.getSubjectName();
		gradeName = grade.getGradeName();
		gpaGradeName = gpa.getGradeName();
	}

	public Long getJournalId() {
		return journalId;
	}

	public String getStudentNameInGroup() {
		return studentNameInGroup;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public String getGradeName() {
		return gradeName;
	}

	public String getTermName() {
		return termName;
	}

	public String getGpaGradeName() {
		return gpaGradeName;
	}

}
