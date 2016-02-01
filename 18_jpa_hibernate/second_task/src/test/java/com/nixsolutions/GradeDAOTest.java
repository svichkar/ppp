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
import com.nixsolutions.studentgrade.entity.Grade;
import com.nixsolutions.studentgrade.util.ConnectionManager;

public class GradeDAOTest {
	private static Connection conn;
	private static IDatabaseConnection iconn;
	private static GradeDAO grade;		


	@BeforeClass
	public static void setUp() throws DatabaseUnitException {
		conn = ConnectionManager.getConnection();
		iconn = new DatabaseConnection(conn);
		grade = DAOFactory.getGrade();
		DAOApp.main(null);
	}

	@AfterClass
	public static void tearDown() throws SQLException {
		conn.close();
		iconn.close();
	}

	@Test
	public void shouldCreateGrade() throws DataSetException {
		Grade gradeNew = new Grade();
		gradeNew.setGradeName("Super");
		grade.createGrade(gradeNew);
		QueryDataSet qDataSet = new QueryDataSet(iconn);
		qDataSet.addTable("grade", "SELECT * FROM grade");
		IDataSet ds = qDataSet;
		ITable table = ds.getTable("grade");
		Assert.assertEquals(gradeNew.getGradeName(), table.getValue(table.getRowCount() - 1, "grade_name"));
		grade.deleteGrade(gradeNew);
	}

	@Test
	public void shouldUpdateGrade() throws DataSetException {
		Grade gradeUpdate = new Grade();
		gradeUpdate.setGradeName("Super");
		grade.createGrade(gradeUpdate);
		gradeUpdate.setGradeName("SuperUpdated");
		grade.updateGrade(gradeUpdate);
		QueryDataSet qDataSet = new QueryDataSet(iconn);
		qDataSet.addTable("grade", "SELECT * FROM grade");
		IDataSet ds = qDataSet;
		ITable table = ds.getTable("grade");
		Assert.assertEquals(gradeUpdate.getGradeName(), table.getValue(table.getRowCount() - 1, "grade_name"));
		grade.deleteGrade(gradeUpdate);
	}

	@Test
	public void shouldDeleteGrade() throws DataSetException {
		Grade gradeDelete = new Grade();
		gradeDelete.setGradeName("Super");
		grade.createGrade(gradeDelete);
		QueryDataSet qDataSetBefore = new QueryDataSet(iconn);
		qDataSetBefore.addTable("grade", "SELECT * FROM grade");
		IDataSet dataSetBefore = qDataSetBefore;
		ITable tableBefore = dataSetBefore.getTable("grade");
		int sizeBefore = tableBefore.getRowCount();
		grade.deleteGrade(gradeDelete);
		QueryDataSet qDataSetAfter = new QueryDataSet(iconn);
		qDataSetAfter.addTable("grade", "SELECT * FROM grade");
		IDataSet dataSetAfter = qDataSetAfter;
		ITable tableAfter = dataSetAfter.getTable("grade");
		int sizeAfter = tableAfter.getRowCount();
		Assert.assertEquals(sizeBefore - 1, sizeAfter);
	}

	@Test
	public void shouldGetAllGrades() throws DataSetException {
		List<Grade> grades = grade.findAllGrades();
		QueryDataSet qDataSet = new QueryDataSet(iconn);
		qDataSet.addTable("grade", "SELECT * FROM grade");
		IDataSet ds = qDataSet;
		ITable table = ds.getTable("grade");
		Assert.assertEquals(table.getRowCount(), grades.size());
	}

	@Test
	public void shouldFindGradeById() throws DataSetException {
		Grade gradeNew = new Grade();
		gradeNew.setGradeName("Super");
		grade.createGrade(gradeNew);
		Grade gradeTest = grade.findGradeById(gradeNew.getGradeId());
		QueryDataSet qDataSet = new QueryDataSet(iconn);
		qDataSet.addTable("grade", "SELECT * FROM grade");
		IDataSet ds = qDataSet;
		ITable table = ds.getTable("grade");
		Assert.assertEquals(table.getValue(table.getRowCount() - 1, "grade_name"), gradeTest.getGradeName());
		grade.deleteGrade(gradeTest);
	}
	
}
