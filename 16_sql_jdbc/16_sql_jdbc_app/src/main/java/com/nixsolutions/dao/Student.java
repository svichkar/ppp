package com.nixsolutions.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Student extends AbstractInstance {
    private int studentId;
    private String firstName;
    private String lastName;
    private Date dateBirthday;
    private Date dateEntry;
    private int studentGroupId;
    private int termId;
    private int statusId;

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

    public List select(String[] fields, String condition) throws ClassNotFoundException, IOException, SQLException {
	List<Student> termResult = new ArrayList<Student>();
	ResultSet result = super.find(fields, condition);
	List<String> colNames = new ArrayList<String>();
	for (int i = 1; i <= result.getMetaData().getColumnCount(); i++) {
	    colNames.add(result.getMetaData().getColumnName(i).toLowerCase());
	}
	while (result.next()) {
	    Student s = new Student();
	    if (colNames.contains("student_id")) {
		s.studentId = result.getInt("student_id");
	    }
	    if (colNames.contains("first_name")) {
		s.firstName = result.getString("first_name");
	    }
	    if (colNames.contains("last_name")) {
		s.lastName = result.getString("last_name");
	    }
	    if (colNames.contains("date_birthday")) {
		s.dateBirthday = result.getDate("date_birthday");
	    }
	    if (colNames.contains("date_entry")) {
		s.dateEntry = result.getDate("date_entry");
	    }
	    if (colNames.contains("student_group_id")) {
		s.studentGroupId = result.getInt("student_group_id");
	    }
	    if (colNames.contains("term_id")) {
		s.termId = result.getInt("term_id");
	    }
	    if (colNames.contains("status_id")) {
		s.statusId = result.getInt("status_id");
	    }
	    termResult.add(s);
	}
	return termResult;
    }
}
