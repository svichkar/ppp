package com.jdbcDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.h2.jdbcx.JdbcConnectionPool;

import com.nixsolutions.ConnectionManager;
import com.nixsolutions.DBCreationThroughDrivermanager;

public class Book implements DbInterface {
	static final Logger LOG = LogManager
			.getLogger(Book.class);
	private int id;
	private String name;
	private String author;
	private String publisher;
	private int category_id;
	private Connection conn;
	private JdbcConnectionPool cp;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public Book() {
		cp = ConnectionManager.getInstance();
	}
	public boolean create(List<String> columns, List<String> elements)
			throws SQLException {
		conn = cp.getConnection();
		conn.setAutoCommit(false);

		if (columns.size() != elements.size()) {
			throw new SQLException("Size of columns and elemnts not the same.");
		}
		Statement st = null;
		try {
			st = conn.createStatement();
			String SQL = "INSERT INTO book (" + String.join(",", columns)
					+ ") VALUES ('" + String.join("','", elements) + "');";
			st.executeUpdate(SQL);
			conn.commit();
			return true;
		} catch (Exception e) {
			conn.rollback();
			LOG.error(e,e);
			return false;
		} finally {
			st.close();
			conn.close();
		}

	}

	public boolean update(List<String> columns, List<String> elements,
			String whereState) throws SQLException {
		conn = cp.getConnection();
		conn.setAutoCommit(false);
		if (columns.size() != elements.size()) {
			throw new SQLException("Size of columns and elemnts not the same.");
		}
		Statement st = null;
		try {
			st = conn.createStatement();
			for (int i = 0; i < columns.size(); i++) {
				st.executeUpdate("UPDATE book SET " + columns.get(i) + "='"
						+ elements.get(i) + "' WHERE " + whereState);
				conn.commit();
			}

			return true;
		} catch (SQLException e) {
			conn.rollback();
		} finally {
			st.close();
			conn.close();
		}

		return false;
	}

	public boolean delete(String whereState) throws SQLException {
		conn = cp.getConnection();
		conn.setAutoCommit(false);
		try {
			conn.createStatement()
					.execute("DELETE FROM book WHERE " + whereState);
			conn.commit();
		} catch (SQLException ex) {
			conn.rollback();
		} finally {

			conn.close();
		}
		return false;
	}

	public ResultSet find(String[] searchField, String searchQuery) {
		ResultSet rs = null;
		try {
			conn = cp.getConnection();
			String fieldSet = "";
			if (searchField != null) {
				fieldSet = String.join(",", searchField);
			} else {
				fieldSet = "*";
			};
			String whereSet = "";
			if (searchQuery != null) {
				whereSet = " WHERE " + searchQuery;
			}

			rs = conn.createStatement().executeQuery(
					"SELECT " + fieldSet + " FROM book " + whereSet + ";");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rs;
	}

}
