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

import com.jdbcDAO.BookInstance;
import com.jdbcDAO.Reader;
import com.jdbcDAO.WorkWithDb;

public class BookInstanceTest {

	Connection conectionInstance;

	public BookInstanceTest() {
		try {
			conectionInstance = ConnectionManager.getInstance().getConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void checkTableSizeThoughDAOandDBunit() {
		BookInstance r = new BookInstance();
		try {
			List<BookInstance> BookInstanceList = r.findElement(null, null);
			IDatabaseConnection connection = new DatabaseConnection(
					conectionInstance);
			QueryDataSet partialDataSet = new QueryDataSet(connection);
			partialDataSet.addTable("bookinstance",
					"SELECT * FROM bookinstance");
			IDataSet set = partialDataSet;
			ITable BookInstanceTable = set.getTable("bookinstance");
			assertEquals(BookInstanceTable.getRowCount(),
					BookInstanceList.size());

			System.out.println(BookInstanceTable.getRowCount() + "|"
					+ BookInstanceList.size());

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DataSetException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void checkThatContentIsTheSameInBothTables() {
		BookInstance r = new BookInstance();
		try {
			List<BookInstance> bookInstanceList = r.findElement(null, null);
			IDatabaseConnection connection = new DatabaseConnection(
					conectionInstance);
			QueryDataSet partialDataSet = new QueryDataSet(connection);
			partialDataSet.addTable("bookinstance",
					"SELECT * FROM bookinstance");
			IDataSet set = partialDataSet;
			ITable bookInstanceTable = set.getTable("bookinstance");
			for (int i = 0; i < bookInstanceList.size(); i++) {
				assertEquals(bookInstanceList.get(i).getId(),
						bookInstanceTable.getValue(i, "id"));
				System.out.println(bookInstanceList.get(i).getId() + "|"
						+ bookInstanceTable.getValue(i, "id"));

				assertEquals(bookInstanceList.get(i).getBook_id(),
						bookInstanceTable.getValue(i, "book_id"));
				System.out.println(bookInstanceList.get(i).getBook_id() + "|"
						+ bookInstanceTable.getValue(i, "book_id"));
				assertEquals(bookInstanceList.get(i).getInventory_number(),
						bookInstanceTable.getValue(i, "inventory_number"));
				System.out.println(bookInstanceList.get(i).getInventory_number()
						+ "|"
						+ bookInstanceTable.getValue(i, "inventory_number"));
			}

			System.out.println(bookInstanceTable.getRowCount() + "|"
					+ bookInstanceList.size());

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DataSetException e) {
			e.printStackTrace();
		}

	}

}
