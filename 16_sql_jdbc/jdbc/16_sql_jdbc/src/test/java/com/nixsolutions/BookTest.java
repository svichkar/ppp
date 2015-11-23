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
import com.nixsolutions.dao.BookFactory;
import com.nixsolutions.entity.Book;
import com.nixsolutions.util.ConnectionManager;
import com.nixsolutions.util.WorkWithDb;

public class BookTest {
	private Connection conectionInstance;

	public BookTest() {
		conectionInstance = ConnectionManager.getInstance();
	}
	@Test
	public void checkTableSizeThoughDAOandDBunit() {

		BookFactory r = new BookFactory();
		try {
			List<Book> BookList = r.find(null, null);
			IDatabaseConnection connection = new DatabaseConnection(
					conectionInstance);
			QueryDataSet partialDataSet = new QueryDataSet(connection);
			partialDataSet.addTable("book", "SELECT * FROM book");
			IDataSet set = partialDataSet;
			ITable BookTable = set.getTable("book");
			assertEquals(BookTable.getRowCount(), BookList.size());

			System.out.println(BookTable.getRowCount() + "|" + BookList.size());

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DataSetException e) {
			e.printStackTrace();
		}

	}
	@Test
	public void checkThatContentIsTheSameInBothTables() {

		BookFactory r = new BookFactory();
		try {
			List<Book> bookList = r.find(null, null);
			IDatabaseConnection connection = new DatabaseConnection(
					conectionInstance);
			QueryDataSet partialDataSet = new QueryDataSet(connection);
			partialDataSet.addTable("book", "SELECT * FROM book");
			IDataSet set = partialDataSet;
			ITable bookTable = set.getTable("book");
			for (int i = 0; i < bookList.size(); i++) {
				assertEquals(bookList.get(i).getId(),
						bookTable.getValue(i, "id"));
				System.out.println(bookList.get(i).getId() + "|"
						+ bookTable.getValue(i, "id"));

				assertEquals(bookList.get(i).getId(),
						bookTable.getValue(i, "id"));
				System.out.println(bookList.get(i).getId() + "|"
						+ bookTable.getValue(i, "id"));

				assertEquals(bookList.get(i).getName(),
						bookTable.getValue(i, "name"));
				System.out.println(bookList.get(i).getName() + "|"
						+ bookTable.getValue(i, "name"));

				assertEquals(bookList.get(i).getAuthor(),
						bookTable.getValue(i, "author"));
				System.out.println(bookList.get(i).getAuthor() + "|"
						+ bookTable.getValue(i, "author"));

				assertEquals(bookList.get(i).getPublisher(),
						bookTable.getValue(i, "publisher"));
				System.out.println(bookList.get(i).getPublisher() + "|"
						+ bookTable.getValue(i, "publisher"));

			}

			System.out.println(bookTable.getRowCount() + "|" + bookList.size());

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DataSetException e) {
			e.printStackTrace();
		}

	}
	@Test
	public void validateBasesAfterMakingSomeChanges() {
		BookFactory book = new BookFactory();
		book.addNewRow(
				Arrays.asList("name", "author", "publisher", "category_id"),
				Arrays.asList(WorkWithDb.generateString(),
						WorkWithDb.generateString(),
						WorkWithDb.generateString(), "1"));

		checkThatContentIsTheSameInBothTables();
	}
}
