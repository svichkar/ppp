package com.nixsolutions.sql_jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBCreation {

	public static void main(String[] args) 
			throws ClassNotFoundException, SQLException, IOException {
		Class.forName("org.h2.Driver");
		Properties props = new Properties();
		FileInputStream fis = new FileInputStream("jdbc.properties");
		props.load(fis);
		Connection conn = DriverManager.getConnection(props.getProperty("DB_DRIVER"),
				props.getProperty("DB_USER"), props.getProperty("DB_PASSWORD"));
		Statement stmt = conn.createStatement();
		stmt.addBatch("");
		stmt.addBatch("");
		stmt.executeBatch();
		stmt.close();
	}

}
