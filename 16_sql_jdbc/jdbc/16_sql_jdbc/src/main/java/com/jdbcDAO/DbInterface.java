package com.jdbcDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DbInterface {

	public boolean create(List<String> columns, List<String> elements)
			throws SQLException;
	public boolean update(List<String> columns, List<String> elements,
			String whereState) throws SQLException;
	public boolean delete(String whereState) throws SQLException;
	public <T> T find(String[] searchField,String searchQuery) throws SQLException;
}
