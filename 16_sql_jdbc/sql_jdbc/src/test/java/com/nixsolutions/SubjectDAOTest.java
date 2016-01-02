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
import com.nixsolutions.dao.impl.SubjectDAOImpl;
import com.nixsolutions.entity.Subject;
import com.nixsolutions.util.ConnectionManager;

public class SubjectDAOTest {
	private Connection conn;
	private IDatabaseConnection iconn;
	private SubjectDAOImpl subject;

	@Before
	public void setUp() throws DatabaseUnitException  {
		conn = ConnectionManager.getConnection();
		iconn = new DatabaseConnection(conn);
		subject = DAOFactory.getSubject();
	}

	@After
	public void tearDown() throws SQLException {
		conn.close();
		iconn.close();
	}

	@Test
	public void shouldCreateSubject() throws DataSetException {
		Subject subjectNew = subject.createSubject(10, "Psychology", 3);
		QueryDataSet qDataSet = new QueryDataSet(iconn);
		qDataSet.addTable("subject", "SELECT * FROM subject");
		IDataSet ds = qDataSet;
		ITable table = ds.getTable("subject");
		Assert.assertEquals(subjectNew.getSubjectName(), table.getValue(subjectNew.getSubjectId() - 1, "subject_name"));
		subject.deleteSubject(subjectNew);
	}

	@Test
	public void shouldUpdateSubject() throws DataSetException {
		subject.createSubject(10, "Psychology", 3);
		Subject subjectUpdate = new Subject(10, "Psychology and Ethics", 3);
		subject.updateSubject(subjectUpdate);
		QueryDataSet qDataSet = new QueryDataSet(iconn);
		qDataSet.addTable("subject", "SELECT * FROM subject");
		IDataSet ds = qDataSet;
		ITable table = ds.getTable("subject");
		Assert.assertEquals(subjectUpdate.getSubjectName(), table.getValue(subjectUpdate.getSubjectId() - 1, "subject_name"));
		subject.deleteSubject(subjectUpdate);
	}

	@Test
	public void shouldDeleteSubject() throws DataSetException {
		Subject subjectDelete = subject.createSubject(10, "Psychology", 3);
		QueryDataSet qDataSetBefore = new QueryDataSet(iconn);
		qDataSetBefore.addTable("subject", "SELECT * FROM subject");
		IDataSet dataSetBefore = qDataSetBefore;
		ITable tableBefore = dataSetBefore.getTable("subject");
		int sizeBefore = tableBefore.getRowCount();
		subject.deleteSubject(subjectDelete);
		QueryDataSet qDataSetAfter = new QueryDataSet(iconn);
		qDataSetAfter.addTable("subject", "SELECT * FROM subject");
		IDataSet dataSetAfter = qDataSetAfter;
		ITable tableAfter = dataSetAfter.getTable("subject");
		int sizeAfter = tableAfter.getRowCount();
		Assert.assertEquals(sizeBefore - 1, sizeAfter);
	}

	@Test
	public void shouldGetAllSubjects() throws DataSetException {
		List<Subject> subjects = subject.findAllSubjects();
		QueryDataSet qDataSet = new QueryDataSet(iconn);
		qDataSet.addTable("subject", "SELECT * FROM subject");
		IDataSet ds = qDataSet;
		ITable table = ds.getTable("subject");
		Assert.assertEquals(table.getRowCount(), subjects.size());
	}

	@Test
	public void shouldFindSubjectById() throws DataSetException {
		Subject subjectTest = subject.findSubjectById(5);
		QueryDataSet qDataSet = new QueryDataSet(iconn);
		qDataSet.addTable("subject", "SELECT * FROM subject");
		IDataSet ds = qDataSet;
		ITable table = ds.getTable("subject");
		Assert.assertEquals(table.getValue(subjectTest.getSubjectId() - 1, "subject_name"), subjectTest.getSubjectName());
	}
}
