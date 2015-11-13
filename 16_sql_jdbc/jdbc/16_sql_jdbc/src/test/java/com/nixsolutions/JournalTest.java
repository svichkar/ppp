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

import com.jdbcDAO.Journal;
import com.jdbcDAO.Reader;
import com.jdbcDAO.WorkWithDb;

public class JournalTest {
	private Connection conectionInstance;

	public JournalTest() {
		try {
			conectionInstance = ConnectionManager.getInstance().getConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void checkTableSizeThoughDAOandDBunit() {
		Journal r = new Journal();
		try {
			List<Reader> journalList = r.findElement(null, null);
			IDatabaseConnection connection = new DatabaseConnection(
					conectionInstance);
			QueryDataSet partialDataSet = new QueryDataSet(connection);
			partialDataSet.addTable("journal", "SELECT * FROM journal");
			IDataSet set = partialDataSet;
			ITable journalTable = set.getTable("journal");
			assertEquals(journalTable.getRowCount(), journalList.size());

			System.out.println(
					journalTable.getRowCount() + "|" + journalList.size());

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DataSetException e) {
			e.printStackTrace();
		}

	}
	@Test
	public void checkThatContentIsTheSameInBothTables() {
		Journal r = new Journal();
		try {
			List<Journal> journalList = r.findElement(null, null);
			IDatabaseConnection connection = new DatabaseConnection(
					conectionInstance);
			QueryDataSet partialDataSet = new QueryDataSet(connection);
			partialDataSet.addTable("journal", "SELECT * FROM journal");
			IDataSet set = partialDataSet;
			ITable journalTable = set.getTable("journal");
			for (int i = 0; i < journalList.size(); i++) {
				assertEquals(journalList.get(i).getId(),
						journalTable.getValue(i, "id"));
				System.out.println(journalList.get(i).getId() + "|"
						+ journalTable.getValue(i, "id"));
				assertEquals(journalList.get(i).getBook_id(),
						journalTable.getValue(i, "book_id"));
				System.out.println(journalList.get(i).getReader_id() + "|"
						+ journalTable.getValue(i, "reader_id"));
				assertEquals(journalList.get(i).getStart_date(),
						journalTable.getValue(i, "start_date"));
				System.out.println(journalList.get(i).getEnd_date() + "|"
						+ journalTable.getValue(i, "end_date"));
			}

			System.out.println(
					journalTable.getRowCount() + "|" + journalList.size());

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DataSetException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void validateBasesAfterMakingSomeChanges() {
		Journal j = new Journal();
		try {
			j.create(
					Arrays.asList("book_id", "reader_id", "start_date",
							"end_date"),
					Arrays.asList("1", "1", "2010-01-01", "2013-10-17"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		checkThatContentIsTheSameInBothTables();
	}
}
