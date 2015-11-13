package com.nixsolutions;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class DBCreationThroughDrivermanager {

	static final Logger LOG = LogManager
			.getLogger(DBCreationThroughDrivermanager.class);

	public static void main(String[] args) {
		BasicConfigurator.configure();
		Connection conn = null;
		Statement st = null;
		try {
			// initializing driver
			Class.forName("org.h2.Driver").newInstance();

			// create conection
			conn = DriverManager.getConnection("jdbc:h2:~/sqllab", "sa", "");
			// declare statement

			// initialize statement
			st = conn.createStatement();
			// execute db creation sqls

			st.executeUpdate(
					"CREATE TABLE IF NOT EXISTS category (id INT PRIMARY KEY AUTO_INCREMENT,name VARCHAR(30) UNIQUE);");
			System.out.println("Table 'category' - created");
			st.executeUpdate(
					"CREATE TABLE IF NOT EXISTS book (id INT PRIMARY KEY AUTO_INCREMENT,name VARCHAR(100),"
							+ " author VARCHAR(50),publisher VARCHAR(50),category_id INT NOT NULL ,FOREIGN KEY "
							+ " (category_id) REFERENCES category (id));");
			System.out.println("Table 'book' - created");
			st.executeUpdate(
					"CREATE TABLE IF NOT EXISTS bookinstance (id INT PRIMARY KEY AUTO_INCREMENT,book_id"
							+ " INT,inventory_number INT,FOREIGN KEY (book_id) REFERENCES book  (id));");
			System.out.println("Table 'bookinstance' - created");
			st.executeUpdate(
					"CREATE TABLE IF NOT EXISTS reader (id INT PRIMARY KEY AUTO_INCREMENT,name VARCHAR(50),adress"
							+ " VARCHAR(50));");
			System.out.println("Table 'reader' - created");
			st.executeUpdate(
					"CREATE TABLE IF NOT EXISTS journal (id INT PRIMARY KEY AUTO_INCREMENT,book_id INT NOT"
							+ " NULL,reader_id INT NOT NULL,start_date DATE NOT NULL,end_date DATE NOT NULL,FOREIGN KEY (book_id)"
							+ " REFERENCES bookinstance  (id),FOREIGN KEY (reader_id) REFERENCES reader  (id));");
			System.out.println("Table 'journal' - created");

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
