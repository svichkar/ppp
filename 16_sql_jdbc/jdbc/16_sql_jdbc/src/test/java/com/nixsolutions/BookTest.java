package com.nixsolutions;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.junit.Test;

import com.jdbcDAO.Book;
import com.jdbcDAO.Reader;
import com.jdbcDAO.WorkWithDb;

public class BookTest {
	private Connection conectionInstance;

	public BookTest() {
		try {
			conectionInstance = ConnectionManager.getInstance().getConnection();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void checkTableSizeThoughDAOandDBunit() {
		Book book = new Book();
		try {
			ResultSet rs = book.find(null, null);
			IDatabaseConnection connection = new DatabaseConnection(
					conectionInstance);
			QueryDataSet partialDataSet = new QueryDataSet(connection);
			partialDataSet.addTable("book", "SELECT * FROM book");
			IDataSet set = partialDataSet;
			ITable bookTable = set.getTable("book");
			List<Book> bookList = new ArrayList<Book>();
			while (rs.next()) {
				Book b = new Book();
				b.setId(rs.getInt("id"));
				b.setName(rs.getString("name"));
				b.setAuthor(rs.getString("author"));
				b.setPublisher(rs.getString("publisher"));
				b.setCategory_id(rs.getInt("category_id"));
				bookList.add(b);
			}
			assertEquals(bookTable.getRowCount(), bookList.size());

			System.out.println(bookTable.getRowCount() + "|" + bookList.size());

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DataSetException e) {
			e.printStackTrace();
		}

	}
	@Test
	public void checkThatContentIsTheSameInBothTables() {
		Book book = new Book();
		try {
			ResultSet rs = book.find(null, null);
			IDatabaseConnection connection = new DatabaseConnection(
					conectionInstance);
			QueryDataSet partialDataSet = new QueryDataSet(connection);
			partialDataSet.addTable("book", "SELECT * FROM book");
			IDataSet set = partialDataSet;
			ITable bookTable = set.getTable("book");
			List<Book> bookList = new ArrayList<Book>();
			while (rs.next()) {
				Book b = new Book();
				b.setId(rs.getInt("id"));
				b.setName(rs.getString("name"));
				b.setAuthor(rs.getString("author"));
				b.setPublisher(rs.getString("publisher"));
				b.setCategory_id(rs.getInt("category_id"));
				bookList.add(b);
			}
			for (int i = 0; i < bookList.size(); i++) {
				assertEquals(bookList.get(i).getId(),
						bookTable.getValue(i, "id"));
				assertEquals(bookList.get(i).getName(),
						bookTable.getValue(i, "name"));
				assertEquals(bookList.get(i).getAuthor(),
						bookTable.getValue(i, "author"));
				assertEquals(bookList.get(i).getPublisher(),
						bookTable.getValue(i, "publisher"));
				assertEquals(bookList.get(i).getCategory_id(),
						bookTable.getValue(i, "category_id"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DataSetException e) {
			e.printStackTrace();
		}

	}
	@Test
	public void validateBasesAfterMakingSomeChanges() {
		Book book = new Book();
		try {
			book.create(
					Arrays.asList("name", "author", "publisher", "category_id"),
					Arrays.asList(WorkWithDb.generateString(),
							WorkWithDb.generateString(),
							WorkWithDb.generateString(), "1"));

			checkThatContentIsTheSameInBothTables();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
