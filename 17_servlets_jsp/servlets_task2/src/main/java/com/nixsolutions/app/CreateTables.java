package com.nixsolutions.app;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateTables {
	public static final Logger LOG = LogManager.getLogger();
	private String url;
	private String name;
	private String pswd;
	
	public static void main(String[] args) throws SQLException {
		CreateTables create = new CreateTables();
		create.setProperties();
		create.createTables();
	}
	
	public void setProperties(){
		InputStream input = getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		Properties prop = new Properties();
		try {
			prop.load(input);
			url = prop.getProperty("host");
			name = prop.getProperty("login");
			pswd = prop.getProperty("password");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void createTables(){
		try (Connection conn = DriverManager.getConnection(url, name, pswd);
				Statement statem = conn.createStatement()){
			Class.forName("org.h2.Driver");
	
			statem.addBatch("CREATE TABLE author "
					+ "(author_id INT IDENTITY, "
					+ "first_name VARCHAR(256) NOT NULL, "
					+ "last_name VARCHAR(256) NOT NULL)");
			statem.addBatch("CREATE TABLE cell "
					+ "(cell_id IDENTITY, "
					+ "name VARCHAR(256) NOT NULL)");
			statem.addBatch("CREATE TABLE category"
					+ "(category_id IDENTITY,"
					+ "name VARCHAR(256) NOT NULL)");
			statem.addBatch("CREATE TABLE client"
					+ "(client_id IDENTITY,"
					+ "first_name VARCHAR(256) NOT NULL,"
					+ "last_name VARCHAR(256) NOT NULL,"
					+ "phone VARCHAR(256),"
					+ "email VARCHAR(256))");
			statem.addBatch("CREATE TABLE book"
					+ "(book_id IDENTITY,"
					+ "name VARCHAR(256) NOT NULL,"
					+ "cell_id BIGINT NOT NULL,"
					+ "category_id BIGINT NOT NULL,"
					+ "FOREIGN KEY (cell_id) REFERENCES cell (cell_id),"
					+ "FOREIGN KEY (category_id) REFERENCES category (category_id))");
			statem.addBatch("CREATE TABLE author_book("
					+ "author_id BIGINT NOT NULL,"
					+ "book_id BIGINT NOT NULL,"
					+ "FOREIGN KEY (author_id) REFERENCES author (author_id),"
					+ "FOREIGN KEY (book_id) REFERENCES book (book_id))");
			statem.addBatch("CREATE TABLE rent_journal"
					+ "(ticket_id IDENTITY,"
					+ "client_id BIGINT NOT NULL,"
					+ "book_id BIGINT NOT NULL,"
					+ "rent_date DATE NOT NULL,"
					+ "return_date DATE,FOREIGN KEY (client_id) REFERENCES client (client_id),"
					+ "FOREIGN KEY (book_id) REFERENCES book (book_id))");
			statem.addBatch("CREATE TABLE role"
					+ "(role_id IDENTITY,"
					+ "role_name VARCHAR(256) NOT NULL)");
			statem.addBatch("CREATE TABLE user"
					+ "(user_id IDENTITY,"
					+ "user_name VARCHAR(256) NOT NULL,"
					+ "user_password VARCHAR(256) NOT NULL,"
					+ "role_id BIGINT NOT NULL,"
					+ "FOREIGN KEY (role_id) REFERENCES role (role_id))");
			statem.executeBatch();
			
			LOG.info("all the tables were created");
		} catch (ClassNotFoundException e) {
			LOG.error(e);
		} catch (SQLException e) {
			LOG.error(e);
		} 
	}
}
