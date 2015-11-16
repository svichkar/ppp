package com.nixsolutions.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Term extends AbstractInstance {
    private int termId;
    private String termAlias;

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

    public List select(String[] fields, String condition) throws ClassNotFoundException, IOException, SQLException{
	List<Term> termResult = new ArrayList<Term>();
	ResultSet result = super.find(fields, condition);
	List<String> colNames = new ArrayList<String>();
	for (int i = 1; i <= result.getMetaData().getColumnCount(); i++){
	    colNames.add(result.getMetaData().getColumnName(i).toLowerCase());
	}
	while (result.next()){
	    Term t = new Term();
	    if (colNames.contains("term_id")){
		t.termId = result.getInt("term_id");
	    }
	    if (colNames.contains("term_alias")){
		t.termAlias = result.getString("term_alias");
	    }
	    termResult.add(t);
	}
	return termResult;
    }
}
