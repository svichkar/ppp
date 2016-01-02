package com.nixsolutions;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.nixsolutions.dao.DAOFactory;
import com.nixsolutions.dao.impl.JournalDAOImpl;
import com.nixsolutions.entity.Journal;
import com.nixsolutions.util.ConnectionManager;

public class JournalDAOTest {
	private Connection conn;
	private IDatabaseConnection iconn;
	private JournalDAOImpl journal;

	@Before
	public void setUp() throws DatabaseUnitException {
		conn = ConnectionManager.getConnection();
		iconn = new DatabaseConnection(conn);
		journal = DAOFactory.getJournal();
	}

	@After
	public void tearDown() throws SQLException {
		conn.close();
		iconn.close();
	}

	@Test
	public void shouldCreateJournal() throws DataSetException {
		Journal journalNew = journal.createJournal(22, 7, 6, 4);
		QueryDataSet qDataSet = new QueryDataSet(iconn);
		qDataSet.addTable("journal", "SELECT * FROM journal");
		IDataSet ds = qDataSet;
		ITable table = ds.getTable("journal");
		Assert.assertTrue(journalNew.getStudentId() == Integer
				.valueOf(table.getValue(journalNew.getJournalId() - 1, "student_id").toString()));
		Assert.assertTrue(journalNew.getSubjectId() == Integer
				.valueOf(table.getValue(journalNew.getJournalId() - 1, "subject_id").toString()));
		Assert.assertTrue(journalNew.getGradeId() == Integer
				.valueOf(table.getValue(journalNew.getJournalId() - 1, "grade_id").toString()));
		journal.deleteJournal(journalNew);
	}

	@Test
	public void shouldUpdateJournal() throws DataSetException {
		journal.createJournal(22, 7, 6, 4);
		Journal journalUpdate = new Journal(22, 7, 6, 5);
		journal.updateJournal(journalUpdate);
		QueryDataSet qDataSet = new QueryDataSet(iconn);
		qDataSet.addTable("journal", "SELECT * FROM journal");
		IDataSet ds = qDataSet;
		ITable table = ds.getTable("journal");
		Assert.assertTrue(journalUpdate.getStudentId() == Integer
				.valueOf(table.getValue(journalUpdate.getJournalId() - 1, "student_id").toString()));
		Assert.assertTrue(journalUpdate.getSubjectId() == Integer
				.valueOf(table.getValue(journalUpdate.getJournalId() - 1, "subject_id").toString()));
		Assert.assertTrue(journalUpdate.getGradeId() == Integer
				.valueOf(table.getValue(journalUpdate.getJournalId() - 1, "grade_id").toString()));
		journal.deleteJournal(journalUpdate);
	}

	@Test
	public void shouldDeleteJournal() throws DataSetException {
		Journal journalDelete = journal.createJournal(22, 7, 6, 4);
		QueryDataSet qDataSetBefore = new QueryDataSet(iconn);
		qDataSetBefore.addTable("journal", "SELECT * FROM journal");
		IDataSet dataSetBefore = qDataSetBefore;
		ITable tableBefore = dataSetBefore.getTable("journal");
		int sizeBefore = tableBefore.getRowCount();
		journal.deleteJournal(journalDelete);
		QueryDataSet qDataSetAfter = new QueryDataSet(iconn);
		qDataSetAfter.addTable("journal", "SELECT * FROM journal");
		IDataSet dataSetAfter = qDataSetAfter;
		ITable tableAfter = dataSetAfter.getTable("journal");
		int sizeAfter = tableAfter.getRowCount();
		Assert.assertEquals(sizeBefore - 1, sizeAfter);
	}

	@Test
	public void shouldGetAllJournals() throws DataSetException {
		List<Journal> journals = journal.findAllJournals();
		QueryDataSet qDataSet = new QueryDataSet(iconn);
		qDataSet.addTable("journal", "SELECT * FROM journal");
		IDataSet ds = qDataSet;
		ITable table = ds.getTable("journal");
		Assert.assertEquals(table.getRowCount(), journals.size());
	}

	@Test
	public void shouldFindJournalById() throws DataSetException {
		Journal journalTest = journal.findJournalById(5);
		QueryDataSet qDataSet = new QueryDataSet(iconn);
		qDataSet.addTable("journal", "SELECT * FROM journal");
		IDataSet ds = qDataSet;
		ITable table = ds.getTable("journal");
		Assert.assertTrue(Integer.valueOf(
				table.getValue(journalTest.getJournalId() - 1, "student_id").toString()) == journalTest.getStudentId());
		Assert.assertTrue(Integer.valueOf(
				table.getValue(journalTest.getSubjectId() - 1, "subject_id").toString()) == journalTest.getSubjectId());
		Assert.assertTrue(Integer.valueOf(
				table.getValue(journalTest.getGradeId() - 1, "grade_id").toString()) == journalTest.getGradeId());
	}
}
