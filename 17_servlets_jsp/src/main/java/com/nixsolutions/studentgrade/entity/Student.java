package com.nixsolutions.studentgrade.entity;

import java.sql.Date;

public class Student {
	private int studentId;
	private String firstName;
	private String lastName;
	private int groupId;
	private Date admissionDate;
	private int statusId;
	private int termId;

	public Student(int studentId, String firstName, String lastName, int groupId, Date admissionDate, int statusId,
			int termId) {
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.groupId = groupId;
		this.admissionDate = admissionDate;
		this.statusId = statusId;
		this.termId = termId;
	}

	public Student() {
		this(0, "default", "default", 0, Date.valueOf("1970-01-01"), 0, 0);
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public Date getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getTermId() {
		return termId;
	}

	public void setTermId(int termId) {
		this.termId = termId;
	}
}
