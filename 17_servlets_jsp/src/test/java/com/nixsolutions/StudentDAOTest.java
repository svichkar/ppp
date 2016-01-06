package com.nixsolutions;

import java.sql.Connection;
import java.sql.Date;
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
import com.nixsolutions.studentgrade.dao.impl.StudentDAOImpl;
import com.nixsolutions.studentgrade.entity.Student;
import com.nixsolutions.studentgrade.util.ConnectionManager;

public class StudentDAOTest {
	private Connection conn;
	private IDatabaseConnection iconn;
	private StudentDAOImpl student;

	@Before
	public void setUp() throws DatabaseUnitException {
		conn = ConnectionManager.getConnection();
		iconn = new DatabaseConnection(conn);
		student = DAOFactory.getStudent();
	}

	@After
	public void tearDown() throws SQLException {
		conn.close();
		iconn.close();
	}

	@Test
	public void shouldCreateStudent() throws DataSetException {
		Student studentNew = student.createStudent(8, "Mark", "Grey", 1, Date.valueOf("2014-09-01"), 1, 2);
		QueryDataSet qDataSet = new QueryDataSet(iconn);
		qDataSet.addTable("student", "SELECT * FROM student");
		IDataSet ds = qDataSet;
		ITable table = ds.getTable("student");
		Assert.assertEquals(studentNew.getFirstName(), table.getValue(studentNew.getStudentId() - 1, "first_name"));
		Assert.assertEquals(studentNew.getLastName(), table.getValue(studentNew.getStudentId() - 1, "last_name"));
		student.deleteStudent(studentNew);
	}

	@Test
	public void shouldUpdateStudent() throws DataSetException {
		student.createStudent(8, "Mark", "Grey", 1, Date.valueOf("2014-09-01"), 1, 2);
		Student studentUpdate = new Student(8, "Mark", "Test", 1, Date.valueOf("2014-09-01"), 1, 2);
		student.updateStudent(studentUpdate);
		QueryDataSet qDataSet = new QueryDataSet(iconn);
		qDataSet.addTable("student", "SELECT * FROM student");
		IDataSet ds = qDataSet;
		ITable table = ds.getTable("student");
		Assert.assertEquals(studentUpdate.getFirstName(), table.getValue(studentUpdate.getStudentId() - 1, "first_name"));
		Assert.assertEquals(studentUpdate.getLastName(), table.getValue(studentUpdate.getStudentId() - 1, "last_name"));
		student.deleteStudent(studentUpdate);
	}

	@Test
	public void shouldDeleteStudent() throws DataSetException {
		Student studentDelete = student.createStudent(8, "Mark", "Grey", 1, Date.valueOf("2014-09-01"), 1, 2);
		QueryDataSet qDataSetBefore = new QueryDataSet(iconn);
		qDataSetBefore.addTable("student", "SELECT * FROM student");
		IDataSet dataSetBefore = qDataSetBefore;
		ITable tableBefore = dataSetBefore.getTable("student");
		int sizeBefore = tableBefore.getRowCount();
		student.deleteStudent(studentDelete);
		QueryDataSet qDataSetAfter = new QueryDataSet(iconn);
		qDataSetAfter.addTable("student", "SELECT * FROM student");
		IDataSet dataSetAfter = qDataSetAfter;
		ITable tableAfter = dataSetAfter.getTable("student");
		int sizeAfter = tableAfter.getRowCount();
		Assert.assertEquals(sizeBefore - 1, sizeAfter);
	}

	@Test
	public void shouldGetAllStudents() throws DataSetException {
		List<Student> students = student.findAllStudents();
		QueryDataSet qDataSet = new QueryDataSet(iconn);
		qDataSet.addTable("student", "SELECT * FROM student");
		IDataSet ds = qDataSet;
		ITable table = ds.getTable("student");
		Assert.assertEquals(table.getRowCount(), students.size());
	}

	@Test
	public void shouldFindStudentById() throws DataSetException {
		Student studentTest = student.findStudentById(5);
		QueryDataSet qDataSet = new QueryDataSet(iconn);
		qDataSet.addTable("student", "SELECT * FROM student");
		IDataSet ds = qDataSet;
		ITable table = ds.getTable("student");
		Assert.assertEquals(table.getValue(studentTest.getStudentId() - 1, "first_name"), studentTest.getFirstName());
		Assert.assertEquals(table.getValue(studentTest.getStudentId() - 1, "last_name"), studentTest.getLastName());		
	}
}
