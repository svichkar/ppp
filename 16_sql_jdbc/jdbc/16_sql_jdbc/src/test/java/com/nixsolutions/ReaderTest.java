package com.nixsolutions;

import static org.junit.Assert.*;

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
import com.nixsolutions.dao.ReaderFactory;
import com.nixsolutions.entity.Reader;
import com.nixsolutions.util.ConnectionManager;
import com.nixsolutions.util.WorkWithDb;

public class ReaderTest {

	private Connection conectionInstance;

	public ReaderTest() {
		conectionInstance = ConnectionManager.getInstance();

	}
	@Test
	public void checkTableSizeThoughDAOandDBunit() {
		ReaderFactory r = new ReaderFactory();
		try {
			List<Reader> readerList = r.find(null, null);
			IDatabaseConnection connection = new DatabaseConnection(
					conectionInstance);
			QueryDataSet partialDataSet = new QueryDataSet(connection);
			partialDataSet.addTable("reader", "SELECT * FROM reader");
			IDataSet set = partialDataSet;
			ITable readerTable = set.getTable("reader");
			assertEquals(readerTable.getRowCount(), readerList.size());

			System.out.println(
					readerTable.getRowCount() + "|" + readerList.size());

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DataSetException e) {
			e.printStackTrace();
		}

	}
	@Test
	public void checkThatContentIsTheSameInBothTables() {
		ReaderFactory r = new ReaderFactory();
		try {
			List<Reader> readerList = r.find(null, null);
			IDatabaseConnection connection = new DatabaseConnection(
					conectionInstance);
			QueryDataSet partialDataSet = new QueryDataSet(connection);
			partialDataSet.addTable("reader", "SELECT * FROM reader");
			IDataSet set = partialDataSet;
			ITable readerTable = set.getTable("reader");
			for (int i = 0; i < readerList.size(); i++) {
				assertEquals(readerList.get(i).getId(),
						readerTable.getValue(i, "id"));
				System.out.println(readerList.get(i).getId() + "|"
						+ readerTable.getValue(i, "id"));
				assertEquals(readerList.get(i).getName(),
						readerTable.getValue(i, "name"));
				System.out.println(readerList.get(i).getName() + "|"
						+ readerTable.getValue(i, "name"));
				assertEquals(readerList.get(i).getAdress(),
						readerTable.getValue(i, "adress"));
				System.out.println(readerList.get(i).getAdress() + "|"
						+ readerTable.getValue(i, "adress"));
			}

			System.out.println(
					readerTable.getRowCount() + "|" + readerList.size());

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DataSetException e) {
			e.printStackTrace();
		}

	}
	@Test
	public void validateBasesAfterMakingSomeChanges() {
		ReaderFactory r = new ReaderFactory();
		r.addNewRow(Arrays.asList("name", "adress"), Arrays.asList(
				WorkWithDb.generateString(), WorkWithDb.generateString()));
		checkThatContentIsTheSameInBothTables();
	}
}
