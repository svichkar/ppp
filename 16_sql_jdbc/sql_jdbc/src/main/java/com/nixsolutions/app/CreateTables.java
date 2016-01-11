package com.nixsolutions.app;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateTables {

	public static Logger LOG = LogManager.getLogger(CreateTables.class.getName());

	public static void main(String[] args) {
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException exc) {
			LOG.error(exc.getMessage());
		}
		Properties properties = new Properties();
		try (FileInputStream fis = new FileInputStream(
				CreateTables.class.getClassLoader().getResource("jdbc.properties").getFile())) {
			properties.load(fis);
		} catch (IOException ex) {
			LOG.error(ex.getMessage());
		}
		try (Connection conn = DriverManager.getConnection(properties.getProperty("HOST"),
				properties.getProperty("USER"), properties.getProperty("PASSWORD"))) {
			try (Statement stmt = conn.createStatement()) {
				LOG.info("DB connection is created");
				LOG.info("Statement is created");
				stmt.execute(
						"CREATE TABLE student_group (group_id BIGINT PRIMARY KEY, group_name VARCHAR(256) NOT NULL);");
				LOG.info("Table student_group is created");
				stmt.execute("CREATE TABLE status (status_id TINYINT PRIMARY KEY, status_name VARCHAR(256) NOT NULL);");
				LOG.info("Table status is created");
				stmt.execute("CREATE TABLE term (term_id BIGINT PRIMARY KEY, term_name VARCHAR(256) NOT NULL);");
				LOG.info("Table term is created");
				stmt.execute(
						"CREATE TABLE student (student_id BIGINT IDENTITY PRIMARY KEY, first_name VARCHAR(256) NOT NULL,"
								+ "last_name  VARCHAR(256) NOT NULL, group_id BIGINT NOT NULL, admission_date VARCHAR(256) NOT NULL,"
								+ "status_id TINYINT NOT NULL, term_id BIGINT, FOREIGN KEY (group_id) REFERENCES student_group (group_id),"
								+ "FOREIGN KEY (status_id) REFERENCES status (status_id), FOREIGN KEY (term_id) REFERENCES term (term_id));");
				LOG.info("Table student is created");
				stmt.execute("CREATE TABLE subject (subject_id BIGINT PRIMARY KEY, subject_name VARCHAR(256),"
						+ "term_id BIGINT, FOREIGN KEY (term_id) REFERENCES term (term_id));");
				LOG.info("Table subject is created");
				stmt.execute("CREATE TABLE grade (grade_id TINYINT PRIMARY KEY, grade_name VARCHAR(256) NOT NULL);");
				LOG.info("Table grade is created");
				stmt.execute("CREATE TABLE journal (journal_id BIGINT IDENTITY PRIMARY KEY, student_id BIGINT,"
						+ "subject_id BIGINT, grade_id TINYINT, FOREIGN KEY (student_id) REFERENCES student (student_id),"
						+ "FOREIGN KEY (subject_id) REFERENCES subject (subject_id), FOREIGN KEY (grade_id) REFERENCES grade (grade_id),);");
				LOG.info("Table journal is created");
			}
		} catch (SQLException e) {
			LOG.error(e);
		}
	}
}