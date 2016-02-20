package com.nixsolutions.studentgrade.bean;

import java.sql.Date;

import com.nixsolutions.studentgrade.entity.Status;
import com.nixsolutions.studentgrade.entity.Student;
import com.nixsolutions.studentgrade.entity.StudentGroup;
import com.nixsolutions.studentgrade.entity.Term;

public class StudentBean {
	private Long studentId;
	private String firstName;
	private String lastName;
	private Date admissionDate;
	private String groupName;
	private String statusName;
	private String termName;
	private String studentNameInGroup;

	public StudentBean() {

	}

	public StudentBean(Student student, StudentGroup group, Status status, Term term) {
		studentId = student.getStudentId();
		firstName = student.getFirstName();
		lastName = student.getLastName();
		admissionDate = student.getAdmissionDate();
		groupName = group.getGroupName();
		statusName = status.getStatusName();
		termName = term.getTermName();
	}

	public StudentBean(Student student, StudentGroup group) {
		studentId = student.getStudentId();
		studentNameInGroup = student.getFirstName() + " " + student.getLastName() + ", " + group.getGroupName();
	}

	public Long getStudentId() {
		return studentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Date getAdmissionDate() {
		return admissionDate;
	}

	public String getGroupName() {
		return groupName;
	}

	public String getStatusName() {
		return statusName;
	}

	public String getTermName() {
		return termName;
	}

	public String getStudentNameInGroup() {
		return studentNameInGroup;
	}

}
