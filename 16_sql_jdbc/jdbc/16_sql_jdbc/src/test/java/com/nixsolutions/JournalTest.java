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
import com.nixsolutions.dao.JournalFactory;
import com.nixsolutions.entity.Journal;
import com.nixsolutions.util.ConnectionManager;
import com.nixsolutions.util.WorkWithDb;

public class JournalTest {
	private Connection conectionInstance;

	public JournalTest() {
		conectionInstance = ConnectionManager.getInstance();
	}
	@Test
	public void checkTableSizeThoughDAOandDBunit() {
		JournalFactory r = new JournalFactory();
		try {
			List<Journal> journalList = r.find(null, null);
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
		JournalFactory r = new JournalFactory();
		try {
			List<Journal> journalList = r.find(null, null);
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
				assertEquals(journalList.get(i).getBook_instance_id(),
						journalTable.getValue(i, "book_instance_id"));
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
		JournalFactory j = new JournalFactory();
		j.addNewRow(
				Arrays.asList("book_instance_id", "reader_id", "start_date",
						"end_date"),
				Arrays.asList("1", "1", "2010-01-01", "2013-10-17"));
		checkThatContentIsTheSameInBothTables();
	}
}
