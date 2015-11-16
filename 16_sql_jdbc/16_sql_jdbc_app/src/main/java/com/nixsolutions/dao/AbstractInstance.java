package com.nixsolutions.dao;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nixsolutions.ConnectionManager;

public abstract class AbstractInstance {
    private Connection conn;

    public void insert(String[] fields, String[] values) throws ClassNotFoundException, IOException, SQLException {
	    if (fields.length != values.length) {
		throw new RuntimeException("Counts of fields and values are different");
	    }
	    conn = ConnectionManager.getConnection();
	    Statement stmt = conn.createStatement();
	    stmt.execute("INSERT INTO " + getClass().getSimpleName().toLowerCase() + "(" + String.join(", ", fields)
		    + ") VALUES ('" + String.join("', '", values) + "')");
	    conn.close();
    }

    public void delete(String condition) throws SQLException, ClassNotFoundException, IOException {
	    conn = ConnectionManager.getConnection();
	    Statement stmt = conn.createStatement();
	    stmt.execute("DELETE FROM " + getClass().getSimpleName().toLowerCase()
		    + (condition != null && condition != "" ? " WHERE " + condition : ""));
	    conn.close();
    }

    protected ResultSet find(String[] fields, String condition) throws ClassNotFoundException, IOException, SQLException {
	    conn = ConnectionManager.getConnection();
	    Statement stmt = conn.createStatement();
	    ResultSet result = stmt.executeQuery(
		    "SELECT " + String.join(", ", fields) + " FROM " + getClass().getSimpleName().toLowerCase()
			    + (condition != null && condition != "" ? " WHERE " + condition : ""));
	    return result;
    }

    public void update(String[] fields, String[] values, String condition)
	    throws ClassNotFoundException, IOException, SQLException {
	    if (fields.length != values.length) {
		throw new RuntimeException("Counts of fields and values are different");
	    }
	    conn = ConnectionManager.getConnection();
	    Statement stmt = conn.createStatement();
	    List<String> setValuesToFields = new ArrayList<String>();
	    for (int i = 0; i < fields.length; i++) {
		setValuesToFields.add(fields[i] + " = '" + values[i] + "'");
	    }
	    stmt.execute("UPDATE " + getClass().getSimpleName().toLowerCase() + " SET "
		    + String.join(", ", setValuesToFields)
		    + (condition != null && condition != "" ? " WHERE " + condition : ""));
	    conn.close();
    }
}
