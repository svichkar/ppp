package com.nixsolutions.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Student {
    private int studentId;
    private String firstName;
    private String lastName;
    private Date dateBirthday;
    private Date dateEntry;
    private int studentGroupId;
    private int termId;
    private int statusId;
    private static Map<String, String> mapColNames;

    public Student() {
    }

    public Student(int studentId, String firstName, String lastName, Date dateBirthday, Date dateEntry,
	    int studentGroupId, int termId, int statusId) {
	this.studentId = studentId;
	this.setFirstName(firstName);
	this.setLastName(lastName);
	this.setDateBirthday(dateBirthday);
	this.setDateEntry(dateEntry);
	this.setStudentGroupId(studentGroupId);
	this.setTermId(termId);
	this.setStatusId(statusId);
	if (mapColNames == null) {
	    mapColNames = new HashMap<String, String>();
	    mapColNames.put("PK_studentId", "student_id");
	    mapColNames.put("firstName", "first_name");
	    mapColNames.put("lastName", "last_name");
	    mapColNames.put("dateBirthday", "date_birthday");
	    mapColNames.put("dateEntry", "date_entry");
	    mapColNames.put("studentGroupId", "student_group_id");
	    mapColNames.put("termId", "term_id");
	    mapColNames.put("statusId", "status_id");
	}
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

    public Date getDateBirthday() {
	return dateBirthday;
    }

    public void setDateBirthday(Date dateBirthday) {
	this.dateBirthday = dateBirthday;
    }

    public Date getDateEntry() {
	return dateEntry;
    }

    public void setDateEntry(Date dateEntry) {
	this.dateEntry = dateEntry;
    }

    public int getStudentGroupId() {
	return studentGroupId;
    }

    public void setStudentGroupId(int studentGroupId) {
	this.studentGroupId = studentGroupId;
    }

    public int getTermId() {
	return termId;
    }

    public void setTermId(int termId) {
	this.termId = termId;
    }

    public int getStatusId() {
	return statusId;
    }

    public void setStatusId(int statusId) {
	this.statusId = statusId;
    }

    public static Map<String, String> getMap() {
	if (mapColNames == null) {
	    mapColNames = new HashMap<String, String>();
	    mapColNames.put("PK_studentId", "student_id");
	    mapColNames.put("firstName", "first_name");
	    mapColNames.put("lastName", "last_name");
	    mapColNames.put("dateBirthday", "date_birthday");
	    mapColNames.put("dateEntry", "date_entry");
	    mapColNames.put("studentGroupId", "student_group_id");
	    mapColNames.put("termId", "term_id");
	    mapColNames.put("statusId", "status_id");
	}
	return mapColNames;
    }
}
