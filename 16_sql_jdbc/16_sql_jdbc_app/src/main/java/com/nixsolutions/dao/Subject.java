package com.nixsolutions.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Subject extends AbstractInstance{
    private int termId;
    private int subjectId;
    private String subjectName;
    public int getTermId() {
	return termId;
    }
    public void setTermId(int termId) {
	this.termId = termId;
    }
    public int getSubjectId() {
	return subjectId;
    }
    public void setSubjectId(int subjectId) {
	this.subjectId = subjectId;
    }
    public String getSubjectName() {
	return subjectName;
    }
    public void setSubjectName(String subjectName) {
	this.subjectName = subjectName;
    }
    
    public List select(String[] fields, String condition) throws ClassNotFoundException, IOException, SQLException{
	List<Subject> termResult = new ArrayList<Subject>();
	ResultSet result = super.find(fields, condition);
	List<String> colNames = new ArrayList<String>();
	for (int i = 1; i <= result.getMetaData().getColumnCount(); i++){
	    colNames.add(result.getMetaData().getColumnName(i).toLowerCase());
	}
	while (result.next()){
	    Subject s = new Subject();
	    if (colNames.contains("subject_name")){
		s.subjectName = result.getString("subject_name");
	    }
	    if (colNames.contains("subject_id")){
		s.subjectId = result.getInt("subject_id");
	    }
	    if (colNames.contains("term_id")){
		s.termId = result.getInt("term_id");
	    }
	    termResult.add(s);
	}
	return termResult;
    }
}
