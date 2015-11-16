package com.nixsolutions.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Journal extends AbstractInstance {
    private int journalId;
    private int studentId;
    private int subjectId;
    private String rate;

    public int getJournalId() {
	return journalId;
    }

    public void setJournalId(int journalId) {
	this.journalId = journalId;
    }

    public int getStudentId() {
	return studentId;
    }

    public void setStudentId(int studentId) {
	this.studentId = studentId;
    }

    public int getSubjectId() {
	return subjectId;
    }

    public void setSubjectId(int subjectId) {
	this.subjectId = subjectId;
    }

    public String getRate() {
	return rate;
    }

    public void setRate(String rate) {
	this.rate = rate;
    }
    
    public List select(String[] fields, String condition) throws ClassNotFoundException, IOException, SQLException{
	List<Journal> termResult = new ArrayList<Journal>();
	ResultSet result = super.find(fields, condition);
	List<String> colNames = new ArrayList<String>();
	for (int i = 1; i <= result.getMetaData().getColumnCount(); i++){
	    colNames.add(result.getMetaData().getColumnName(i).toLowerCase());
	}
	while (result.next()){
	    Journal s = new Journal();
	    if (colNames.contains("journal_id")){
		s.journalId = result.getInt("journal_id");
	    }
	    if (colNames.contains("student_id")){
		s.studentId = result.getInt("student_id");
	    }
	    if (colNames.contains("subject_id")){
		s.subjectId = result.getInt("subject_id");
	    }
	    if (colNames.contains("rate")){
		s.rate = result.getString("rate");
	    }
	    termResult.add(s);
	}
	return termResult;
    }
}
