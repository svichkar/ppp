package com.nixsolutions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class DropAllTablesFromDB {
	static final Logger LOG = LogManager.getLogger(DropAllTablesFromDB.class);
	public static void main(String[] args) {

		BasicConfigurator.configure();
		Connection conn = null;
		Statement st = null;
		String[] listOfTableNames = new String[]{"category", "book",
				"bookinstance", "journal", "reader"};
		try {
			// initializing driver
			Class.forName("org.h2.Driver").newInstance();
			// create conection
			conn = DriverManager.getConnection("jdbc:h2:~/sqllab", "sa", "");

			// initialize statement
			st = conn.createStatement();
			for (String string : listOfTableNames) {
				try {
					st.execute("DROP TABLE " + string);
					System.out.println("Table " + string + " was droped");
				} catch (Exception e) {
					LOG.error(e, e);
				}

			}
		} catch (InstantiationException e) {
			LOG.error(e, e);
		} catch (IllegalAccessException e) {
			LOG.error(e, e);
		} catch (ClassNotFoundException e) {
			LOG.error(e, e);
		} catch (SQLException e) {
			LOG.error(e, e);
		} finally {
			try {
				st.close();
				conn.close();
			} catch (SQLException e) {
				LOG.error(e, e);
			}

		}

	}
}
