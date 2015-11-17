package com.nixsolutions;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DropDatabase {

	private final static Logger LOG = LogManager.getLogger(DropDatabase.class);
	private static Properties props;

	public static void main(String[] args) throws ClassNotFoundException {
		Class cls =  Class.forName("org.h2.Driver");
		props = new Properties();
		// get path to properties file
		ClassLoader cloader = cls.getClassLoader();
		String fileWithSettings = cloader.getResource("jdbc.properties")
				.getFile();

		try (FileInputStream fs = new FileInputStream(fileWithSettings)) {
			props.load(fs);
		} catch (IOException ex) {
			LOG.debug(ex, ex);
		}

		try {
			Connection conn = DriverManager.getConnection(
					props.getProperty("CONNECTION_STRING"),
					props.getProperty("USER_ID"),
					props.getProperty("USER_PASSWORD"));
			Statement stat = conn.createStatement();
			stat.addBatch("DROP SCHEMA sqllab;");
			stat.executeBatch();
			stat.close();
		} catch (SQLException ex) {
			LOG.debug(ex, ex);
		}

	}
}
