package com.nixsolutions;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.junit.Test;

import com.jdbcDAO.Category;
import com.jdbcDAO.Reader;
import com.jdbcDAO.WorkWithDb;

public class CategoryTest {
	private Connection conectionInstance;

	public CategoryTest() {
		try {
			conectionInstance = ConnectionManager.getInstance().getConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void checkTableSizeThoughDAOandDBunit() {
		Category r = new Category();
		try {
			List<Category> categoryList = r.findElement(null, null);
			IDatabaseConnection connection = new DatabaseConnection(
					conectionInstance);
			QueryDataSet partialDataSet = new QueryDataSet(connection);
			partialDataSet.addTable("Category", "SELECT * FROM Category");
			IDataSet set = partialDataSet;
			ITable categoryTable = set.getTable("Category");
			assertEquals(categoryTable.getRowCount(), categoryList.size());

			System.out.println(
					categoryTable.getRowCount() + "|" + categoryList.size());

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DataSetException e) {
			e.printStackTrace();
		}

	}
	@Test
	public void checkThatContentIsTheSameInBothTables() {
		Category r = new Category();
		try {
			List<Category> categoryList = r.findElement(null, null);
			IDatabaseConnection connection = new DatabaseConnection(
					conectionInstance);
			QueryDataSet partialDataSet = new QueryDataSet(connection);
			partialDataSet.addTable("category", "SELECT * FROM category");
			IDataSet set = partialDataSet;
			ITable categoryTable = set.getTable("category");
			for (int i = 0; i < categoryList.size(); i++) {
				assertEquals(categoryList.get(i).getId(),
						categoryTable.getValue(i, "id"));
				System.out.println(categoryList.get(i).getId() + "|"
						+ categoryTable.getValue(i, "id"));

				assertEquals(categoryList.get(i).getName(),
						categoryTable.getValue(i, "name"));
				System.out.println(categoryList.get(i).getName() + "|"
						+ categoryTable.getValue(i, "name"));

			}

			System.out.println(
					categoryTable.getRowCount() + "|" + categoryList.size());

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DataSetException e) {
			e.printStackTrace();
		}

	}
	@Test
	public void validateBasesAfterMakingSomeChanges() {
		Category r = new Category();
		try {
			r.create(Arrays.asList("name"),
					Arrays.asList(WorkWithDb.generateString()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		checkThatContentIsTheSameInBothTables();
	}
}
