package com.jdbcDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.h2.jdbcx.JdbcConnectionPool;

import com.nixsolutions.ConnectionManager;

public abstract class Factory {

	public String tableName = this.getClass().getSimpleName().toLowerCase();
	private static JdbcConnectionPool cp = ConnectionManager.getInstance();
	private Connection conn;

	public boolean create(List<String> columns, List<String> elements)
			throws SQLException {
		conn = cp.getConnection();

		if (columns.size() != elements.size()) {
			throw new SQLException("Size of columns and elemnts not the same.");
		}
		Statement st = null;
		try {
			st = conn.createStatement();
			String SQL = "INSERT INTO " + tableName + " ("
					+ String.join(",", columns) + ") VALUES ('"
					+ String.join("','", elements) + "');";
			st.executeUpdate(SQL);

			return true;
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
			return false;
		} finally {
			st.close();
			conn.close();
		}

	}

	public boolean update(List<String> columns, List<String> elements,
			String whereState) throws SQLException {
		conn = cp.getConnection();
		if (columns.size() != elements.size()) {
			throw new SQLException("Size of columns and elemnts not the same.");
		}
		Statement st = null;
		try {
			st = conn.createStatement();
			for (int i = 0; i < columns.size(); i++) {
				st.executeUpdate(
						"UPDATE " + tableName + " SET " + columns.get(i) + "='"
								+ elements.get(i) + "' WHERE " + whereState);
				conn.commit();
			}

			return true;
		} catch (Exception e) {
			throw new SQLException(e);
		}
	}

	public boolean delete(String whereState) throws SQLException {
		try {
			conn = cp.getConnection();
			conn.createStatement().execute(
					"DELETE FROM " + tableName + " WHERE " + whereState);
			return true;

		} catch (Exception ex) {

			throw new SQLException(ex);
		}

	}

	public ResultSet find(String[] searchField, String searchQuery)
			throws SQLException {
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
		ResultSet rs = conn.createStatement().executeQuery(
				"SELECT " + fieldSet + " FROM " + tableName + whereSet + ";");

		return rs;
	}
}
