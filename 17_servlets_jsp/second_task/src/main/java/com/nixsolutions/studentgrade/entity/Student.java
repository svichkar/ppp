package com.nixsolutions.studentgrade.entity;

import java.sql.Date;

public class Student {
	private Long studentId;
	private String firstName;
	private String lastName;
	private Long groupId;
	private Date admissionDate;
	private int statusId;
	private Long termId;

	public Student(Long studentId, String firstName, String lastName, Long groupId, Date admissionDate,
			int statusId, Long termId) {
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.groupId = groupId;
		this.admissionDate = admissionDate;
		this.statusId = statusId;
		this.termId = termId;
	}

	public Student() {
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
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

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
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

	public Long getTermId() {
		return termId;
	}

	public void setTermId(Long termId) {
		this.termId = termId;
	}
}
