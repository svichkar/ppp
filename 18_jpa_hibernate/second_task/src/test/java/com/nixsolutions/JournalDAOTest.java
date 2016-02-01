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
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.nixsolutions.studentgrade.app.DAOApp;
import com.nixsolutions.studentgrade.dao.DAOFactory;
import com.nixsolutions.studentgrade.dao.GradeDAO;
import com.nixsolutions.studentgrade.dao.JournalDAO;
import com.nixsolutions.studentgrade.dao.StudentDAO;
import com.nixsolutions.studentgrade.dao.SubjectDAO;
import com.nixsolutions.studentgrade.entity.Journal;
import com.nixsolutions.studentgrade.util.ConnectionManager;

public class JournalDAOTest {
	private static Connection conn;
	private static IDatabaseConnection iconn;
	private static JournalDAO journal;
	private static StudentDAO student;
	private static SubjectDAO subject;
	private static GradeDAO grade;

	@BeforeClass
	public static void setUp() throws DatabaseUnitException {
		conn = ConnectionManager.getConnection();
		iconn = new DatabaseConnection(conn);
		journal = DAOFactory.getJournal();
		student = DAOFactory.getStudent();
		subject = DAOFactory.getSubject();
		grade = DAOFactory.getGrade();
		DAOApp.main(null);
	}

	@AfterClass
	public static void tearDown() throws SQLException {
		conn.close();
		iconn.close();
	}

	@Test
	public void shouldCreateJournal() throws DataSetException {
		Journal journalNew = new Journal();
		journalNew.setStudent(student.findStudentById(Long.valueOf(7)));
		journalNew.setSubject(subject.findSubjectById(Long.valueOf(6)));
		journalNew.setGrade(grade.findGradeById(Long.valueOf(4)));
		journal.createJournal(journalNew);
		QueryDataSet qDataSet = new QueryDataSet(iconn);
		qDataSet.addTable("journal", "SELECT * FROM journal");
		IDataSet ds = qDataSet;
		ITable table = ds.getTable("journal");
		Assert.assertTrue(journalNew.getStudent().getStudentId() == Long
				.valueOf(table.getValue(table.getRowCount() - 1, "student_id").toString()));
		Assert.assertTrue(journalNew.getSubject().getSubjectId() == Long
				.valueOf(table.getValue(table.getRowCount() - 1, "subject_id").toString()));
		Assert.assertTrue(journalNew.getGrade().getGradeId() == Long
				.valueOf(table.getValue(table.getRowCount() - 1, "grade_id").toString()));
		journal.deleteJournal(journalNew);
	}

	@Test
	public void shouldUpdateJournal() throws DataSetException {
		Journal journalUpdate = new Journal();
		journalUpdate.setStudent(student.findStudentById(Long.valueOf(7)));
		journalUpdate.setSubject(subject.findSubjectById(Long.valueOf(6)));
		journalUpdate.setGrade(grade.findGradeById(Long.valueOf(4)));
		journal.createJournal(journalUpdate);
		journalUpdate.setGrade(grade.findGradeById(Long.valueOf(5)));
		journal.updateJournal(journalUpdate);
		QueryDataSet qDataSet = new QueryDataSet(iconn);
		qDataSet.addTable("journal", "SELECT * FROM journal");
		IDataSet ds = qDataSet;
		ITable table = ds.getTable("journal");
		Assert.assertTrue(journalUpdate.getStudent().getStudentId() == Long
				.valueOf(table.getValue(table.getRowCount() - 1, "student_id").toString()));
		Assert.assertTrue(journalUpdate.getSubject().getSubjectId() == Long
				.valueOf(table.getValue(table.getRowCount() - 1, "subject_id").toString()));
		Assert.assertTrue(journalUpdate.getGrade().getGradeId() == Long
				.valueOf(table.getValue(table.getRowCount() - 1, "grade_id").toString()));
		journal.deleteJournal(journalUpdate);
	}

	@Test
	public void shouldDeleteJournal() throws DataSetException {
		Journal journalDelete = new Journal();
		journalDelete.setStudent(student.findStudentById(Long.valueOf(7)));
		journalDelete.setSubject(subject.findSubjectById(Long.valueOf(6)));
		journalDelete.setGrade(grade.findGradeById(Long.valueOf(4)));
		journal.createJournal(journalDelete);
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
		Journal journalNew = new Journal();
		journalNew.setStudent(student.findStudentById(Long.valueOf(7)));
		journalNew.setSubject(subject.findSubjectById(Long.valueOf(6)));
		journalNew.setGrade(grade.findGradeById(Long.valueOf(4)));
		journal.createJournal(journalNew);
		Journal journalTest = journal.findJournalById(journalNew.getJournalId());
		QueryDataSet qDataSet = new QueryDataSet(iconn);
		qDataSet.addTable("journal", "SELECT * FROM journal");
		IDataSet ds = qDataSet;
		ITable table = ds.getTable("journal");
		Assert.assertTrue(Long
				.valueOf(table.getValue(table.getRowCount() - 1, "student_id").toString()) == journalTest.getStudent()
						.getStudentId());
		Assert.assertTrue(Long
				.valueOf(table.getValue(table.getRowCount() - 1, "subject_id").toString()) == journalTest.getSubject()
						.getSubjectId());
		Assert.assertTrue(Long.valueOf(
				table.getValue(table.getRowCount() - 1, "grade_id").toString()) == journalTest.getGrade().getGradeId());
		journal.deleteJournal(journalTest);
	}
}
