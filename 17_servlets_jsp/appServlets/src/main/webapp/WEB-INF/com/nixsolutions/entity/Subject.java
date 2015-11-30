package com.nixsolutions.entity;

import java.util.HashMap;
import java.util.Map;

public class Subject {
    private int termId;
    private int subjectId;
    private String subjectName;
    private static Map<String, String> mapColNames;

    public Subject() {
    }

    public Subject(int termId, int subjectId, String subjectName) {
	this.termId = termId;
	this.subjectId = subjectId;
	this.subjectName = subjectName;
	if (mapColNames == null) {
	    mapColNames = new HashMap<String, String>();
	    mapColNames.put("termId", "term_id");
	    mapColNames.put("PK_subjectId", "subject_id");
	    mapColNames.put("subjectName", "subject_name");
	}
    }

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

    public static Map<String, String> getMap() {
	if (mapColNames == null) {
	    mapColNames = new HashMap<String, String>();
	    mapColNames.put("termId", "term_id");
	    mapColNames.put("PK_subjectId", "subject_id");
	    mapColNames.put("subjectName", "subject_name");
	}
	return mapColNames;
    }
}
