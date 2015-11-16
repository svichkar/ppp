package com.nixsolutions.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nixsolutions.ConnectionManager;

public class TermDaoWithManualControl {
    private Connection conn;
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

    public void insert(String[] fields, String[] values) throws ClassNotFoundException, IOException, SQLException {
	if (fields.length != values.length) {
	    throw new RuntimeException("Counts of fields and values are different");
	}
	conn = ConnectionManager.getConnection();
	conn.setAutoCommit(false);
	Statement stmt = conn.createStatement();
	stmt.execute("INSERT INTO term " + "(" + String.join(", ", fields)
		+ ") VALUES ('" + String.join("', '", values) + "')");
	conn.commit();
    }

    public void delete(String condition) throws SQLException, ClassNotFoundException, IOException {
	conn = ConnectionManager.getConnection();
	conn.setAutoCommit(false);
	Statement stmt = conn.createStatement();
	stmt.execute("DELETE FROM term "
		+ (condition != null && condition != "" ? " WHERE " + condition : ""));
	conn.commit();
    }

    public List<TermDaoWithManualControl> find(String[] fields, String condition)
	    throws ClassNotFoundException, IOException, SQLException {
	conn = ConnectionManager.getConnection();
	Statement stmt = conn.createStatement();
	ResultSet result = stmt.executeQuery(
		"SELECT " + String.join(", ", fields) + " FROM term "
			+ (condition != null && condition != "" ? " WHERE " + condition : ""));
	List<TermDaoWithManualControl> termResult = new ArrayList<TermDaoWithManualControl>();
	List<String> colNames = new ArrayList<String>();
	for (int i = 1; i <= result.getMetaData().getColumnCount(); i++) {
	    colNames.add(result.getMetaData().getColumnName(i).toLowerCase());
	}
	while (result.next()) {
	    TermDaoWithManualControl t = new TermDaoWithManualControl();
	    if (colNames.contains("term_id")) {
		t.termId = result.getInt("term_id");
	    }
	    if (colNames.contains("term_alias")) {
		t.termAlias = result.getString("term_alias");
	    }
	    termResult.add(t);
	}
	return termResult;
    }

    public void update(String[] fields, String[] values, String condition)
	    throws ClassNotFoundException, IOException, SQLException {
	if (fields.length != values.length) {
	    throw new RuntimeException("Counts of fields and values are different");
	}
	conn = ConnectionManager.getConnection();
	conn.setAutoCommit(false);
	Statement stmt = conn.createStatement();
	List<String> setValuesToFields = new ArrayList<String>();
	for (int i = 0; i < fields.length; i++) {
	    setValuesToFields.add(fields[i] + " = '" + values[i] + "'");
	}
	stmt.execute(
		"UPDATE term " + " SET " + String.join(", ", setValuesToFields)
			+ (condition != null && condition != "" ? " WHERE " + condition : ""));
	conn.close();
    }
}
