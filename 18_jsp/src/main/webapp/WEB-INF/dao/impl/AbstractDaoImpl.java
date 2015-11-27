package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import util.ConnectionManager;

public abstract class AbstractDaoImpl {
	static final Logger LOG = LogManager.getLogger(AbstractDaoImpl.class);
	public String tableName;
	private Connection conn;
	public AbstractDaoImpl() {
		tableName = this.getClass().getSimpleName().toLowerCase()
				.replace("daoimpl", "");

	}
	public boolean addNewRow(List<String> columns, List<String> elements) {

		BasicConfigurator.configure();
		try (Connection connect = ConnectionManager.getConnection()) {
			if (columns.size() != elements.size()) {
				LOG.error("Size of columns and elemnts not the same."
						+ this.getClass());
			}

			try (Statement st = connect.createStatement()) {
				String SQL = "INSERT INTO " + tableName + " ("
						+ String.join(",", columns) + ") VALUES ('"
						+ String.join("','", elements) + "');";
				st.executeUpdate(SQL);
			} catch (Exception e) {
				connect.rollback();
				LOG.error(e, e);
				return false;
			}
			return true;
		} catch (Exception e) {
			try {
				LOG.error(e, e);
				return false;
			} catch (Exception ex) {
				LOG.error(ex, ex);

			}

		}
		return false;

	}

	public boolean update(List<String> columns, List<String> elements,
			String whereState) {

		if (columns.size() != elements.size()) {
			LOG.error("Size of columns and elemnts not the same."
					+ this.getClass());
		}

		try (Connection connect = ConnectionManager.getConnection()) {
			try (Statement st = connect.createStatement()) {

				for (int i = 0; i < columns.size(); i++) {
					st.executeUpdate("UPDATE " + tableName + " SET "
							+ columns.get(i) + "='" + elements.get(i)
							+ "' WHERE " + whereState);

				}

				return true;
			}

		} catch (Exception e) {
			LOG.error(e, e);
			return false;
		}

	}

	public boolean delete(String whereState) {

		try (Connection connect = ConnectionManager.getConnection();) {
			try (Statement statement = connect.createStatement()) {

				statement.execute(
						"DELETE FROM " + tableName + " WHERE " + whereState);
				return true;
			}
		} catch (Exception e) {
			LOG.error(e, e);
			return false;
		}

	}

	public List find(String[] searchField, String searchQuery) {
		List<Object> table = null;

		try (Connection connect = ConnectionManager.getConnection()) {

			String fieldSet = "";
			if (searchField != null) {
				fieldSet = String.join(",", searchField);
			} else {
				fieldSet = "*";
			}
			String whereSet = "";
			if (searchQuery != null) {
				whereSet = " WHERE " + searchQuery;
			}
			try (Statement statement = connect.createStatement()) {
				ResultSet rs = statement.executeQuery("SELECT " + fieldSet
						+ " FROM " + tableName + whereSet + ";");

				ResultSetMetaData rsmd = rs.getMetaData();
				int columnCount = rsmd.getColumnCount();
				table = new ArrayList<Object>();
				while (rs.next()) {
					
					Object tableRow = Class.forName("entity." + this.getClass()
							.getSimpleName().replace("DaoImpl", ""))
							.newInstance();
					for (int i = 1; i < columnCount + 1; i++) {
						String name = rsmd.getColumnName(i);
						Arrays.stream(tableRow.getClass().getMethods())
								.filter(p -> p.getName().toLowerCase()
										.contains("set" + name.toLowerCase()))
								.collect(Collectors.toList()).get(0)
								.invoke(tableRow, rs.getObject(i));
					}
					table.add(tableRow);
				}
			}
		} catch (Exception e) {
			LOG.error(e, e);
		}

		return table;
	}

}
