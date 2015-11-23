package com.nixsolutions.entity;

import java.util.HashMap;
import java.util.Map;

public class Journal {
    private int journalId;
    private int studentId;
    private int subjectId;
    private String rate;
    private static Map<String, String> mapColNames;

    public Journal() {
    }

    public Journal(int journalId, int studentId, int subjectId, String rate) {
	this.journalId = journalId;
	this.studentId = studentId;
	this.subjectId = subjectId;
	this.rate = rate;
	if (mapColNames == null) {
	    mapColNames = new HashMap<String, String>();
	    mapColNames.put("PK_journalId", "journal_id");
	    mapColNames.put("studentId", "student_id");
	    mapColNames.put("subjectId", "subject_id");
	    mapColNames.put("rate", "rate");
	}
    }

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

    public static Map<String, String> getMap() {
	if (mapColNames == null) {
	    mapColNames = new HashMap<String, String>();
	    mapColNames.put("PK_journalId", "journal_id");
	    mapColNames.put("studentId", "student_id");
	    mapColNames.put("subjectId", "subject_id");
	    mapColNames.put("rate", "rate");
	}
	return mapColNames;
    }
}
