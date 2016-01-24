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

import com.nixsolutions.studentgrade.dao.DAOFactory;
import com.nixsolutions.studentgrade.dao.SubjectDAO;
import com.nixsolutions.studentgrade.dao.TermDAO;
import com.nixsolutions.studentgrade.entity.Subject;
import com.nixsolutions.studentgrade.util.ConnectionManager;

public class SubjectDAOTest {
	private Connection conn;
	private IDatabaseConnection iconn;
	private SubjectDAO subject;
	private TermDAO term;

	@Before
	public void setUp() throws DatabaseUnitException {
		conn = ConnectionManager.getConnection();
		iconn = new DatabaseConnection(conn);
		subject = DAOFactory.getSubject();
		term = DAOFactory.getTerm();
	}

	@After
	public void tearDown() throws SQLException {
		conn.close();
		iconn.close();
	}

	@Test
	public void shouldCreateSubject() throws DataSetException {
		Subject subjectNew = new Subject();
		subjectNew.setSubjectName("Psychology");
		subjectNew.setTerm(term.findTermById(Long.valueOf(3)));		
		subject.createSubject(subjectNew);
		QueryDataSet qDataSet = new QueryDataSet(iconn);
		qDataSet.addTable("subject", "SELECT * FROM subject");
		IDataSet ds = qDataSet;
		ITable table = ds.getTable("subject");
		Assert.assertEquals(subjectNew.getSubjectName(),
				table.getValue(table.getRowCount() - 1, "subject_name"));
		subject.deleteSubject(subjectNew);
	}

	@Test
	public void shouldUpdateSubject() throws DataSetException {
		Subject subjectUpdate = new Subject();
		subjectUpdate.setSubjectName("Psychology");
		subjectUpdate.setTerm(term.findTermById(Long.valueOf(3)));		
		subject.createSubject(subjectUpdate);
		subjectUpdate.setSubjectName("Psychology and Ethics");
		subject.updateSubject(subjectUpdate);
		QueryDataSet qDataSet = new QueryDataSet(iconn);
		qDataSet.addTable("subject", "SELECT * FROM subject");
		IDataSet ds = qDataSet;
		ITable table = ds.getTable("subject");
		Assert.assertEquals(subjectUpdate.getSubjectName(),
				table.getValue(table.getRowCount() - 1, "subject_name"));
		subject.deleteSubject(subjectUpdate);
	}

	@Test
	public void shouldDeleteSubject() throws DataSetException {
		Subject subjectDelete = new Subject();
		subjectDelete.setSubjectName("Psychology");
		subjectDelete.setTerm(term.findTermById(Long.valueOf(3)));
		subject.createSubject(subjectDelete);
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
		Subject subjectNew = new Subject();
		subjectNew.setSubjectName("Psychology");
		subjectNew.setTerm(term.findTermById(Long.valueOf(3)));		
		subject.createSubject(subjectNew);
		Subject subjectTest = subject.findSubjectById(Long.valueOf(subjectNew.getSubjectId()));
		QueryDataSet qDataSet = new QueryDataSet(iconn);
		qDataSet.addTable("subject", "SELECT * FROM subject");
		IDataSet ds = qDataSet;
		ITable table = ds.getTable("subject");
		Assert.assertEquals(table.getValue(table.getRowCount() - 1, "subject_name"),
				subjectTest.getSubjectName());
		subject.deleteSubject(subjectNew);
	}
}
