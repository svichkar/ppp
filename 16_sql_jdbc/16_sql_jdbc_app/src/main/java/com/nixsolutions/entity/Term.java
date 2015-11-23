package com.nixsolutions.entity;

import java.util.HashMap;
import java.util.Map;

public class Term {
    private int termId;
    private String termAlias;
    private static Map<String, String> mapColNames;

    public Term() {
    }

    public Term(int termId, String termAlias) {
	this.termId = termId;
	this.termAlias = termAlias;
	if (mapColNames == null) {
	    mapColNames = new HashMap<String, String>();
	    mapColNames.put("PK_termId", "term_id");
	    mapColNames.put("termAlias", "term_alias");
	}
    }

    public int getTermId() {
	return termId;
    }

    public void setTermId(int termId) {
	this.termId = termId;
    }

    public String getTermAlias() {
	return termAlias;
    }

    public void setTermAlias(String termAlias) {
	this.termAlias = termAlias;
    }

    public static Map<String, String> getMap() {
	if (mapColNames == null) {
	    mapColNames = new HashMap<String, String>();
	    mapColNames.put("PK_termId", "term_id");
	    mapColNames.put("termAlias", "term_alias");
	}
	return mapColNames;
    }

}
