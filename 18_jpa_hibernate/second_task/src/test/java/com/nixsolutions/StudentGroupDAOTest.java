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
import com.nixsolutions.studentgrade.dao.StudentGroupDAO;
import com.nixsolutions.studentgrade.entity.StudentGroup;
import com.nixsolutions.studentgrade.util.ConnectionManager;

public class StudentGroupDAOTest {
	private static Connection conn;
	private static IDatabaseConnection iconn;
	private static StudentGroupDAO group;

	@BeforeClass
	public static void setUp() throws DatabaseUnitException {
		conn = ConnectionManager.getConnection();
		iconn = new DatabaseConnection(conn);
		group = DAOFactory.getStudentGroup();
		DAOApp.main(null);
	}

	@AfterClass
	public static void tearDown() throws SQLException {
		conn.close();
		iconn.close();
	}

	@Test
	public void shouldCreateStudentGroup() throws DataSetException {
		StudentGroup groupNew = new StudentGroup();
		groupNew.setGroupName("KI-2016");
		group.createStudentGroup(groupNew);
		QueryDataSet qDataSet = new QueryDataSet(iconn);
		qDataSet.addTable("student_group", "SELECT * FROM student_group");
		IDataSet ds = qDataSet;
		ITable table = ds.getTable("student_group");
		Assert.assertEquals(groupNew.getGroupName(), table.getValue(table.getRowCount() - 1, "group_name"));
		group.deleteStudentGroup(groupNew);
	}

	@Test
	public void shouldUpdateStudentGroup() throws DataSetException {
		StudentGroup groupUpdate = new StudentGroup();
		groupUpdate.setGroupName("KI-2016");
		group.createStudentGroup(groupUpdate);
		groupUpdate.setGroupName("KI-2017");
		group.updateStudentGroup(groupUpdate);
		QueryDataSet qDataSet = new QueryDataSet(iconn);
		qDataSet.addTable("student_group", "SELECT * FROM student_group");
		IDataSet ds = qDataSet;
		ITable table = ds.getTable("student_group");
		Assert.assertEquals(groupUpdate.getGroupName(),
				table.getValue(table.getRowCount() - 1, "group_name"));
		group.deleteStudentGroup(groupUpdate);
	}

	@Test
	public void shouldDeleteStudentGroup() throws DataSetException {
		StudentGroup groupDelete = new StudentGroup();
		groupDelete.setGroupName("KI-2016");
		group.createStudentGroup(groupDelete);
		QueryDataSet qDataSetBefore = new QueryDataSet(iconn);
		qDataSetBefore.addTable("student_group", "SELECT * FROM student_group");
		IDataSet dataSetBefore = qDataSetBefore;
		ITable tableBefore = dataSetBefore.getTable("student_group");
		int sizeBefore = tableBefore.getRowCount();
		group.deleteStudentGroup(groupDelete);
		QueryDataSet qDataSetAfter = new QueryDataSet(iconn);
		qDataSetAfter.addTable("student_group", "SELECT * FROM student_group");
		IDataSet dataSetAfter = qDataSetAfter;
		ITable tableAfter = dataSetAfter.getTable("student_group");
		int sizeAfter = tableAfter.getRowCount();
		Assert.assertEquals(sizeBefore - 1, sizeAfter);
	}

	@Test
	public void shouldGetAllStudentGroups() throws DataSetException {
		List<StudentGroup> groups = group.findAllStudentGroups();
		QueryDataSet qDataSet = new QueryDataSet(iconn);
		qDataSet.addTable("student_group", "SELECT * FROM student_group");
		IDataSet ds = qDataSet;
		ITable table = ds.getTable("student_group");
		Assert.assertEquals(table.getRowCount(), groups.size());
	}

	@Test
	public void shouldFindStudentGroupById() throws DataSetException {
		StudentGroup groupNew = new StudentGroup();
		groupNew.setGroupName("KI-2016");
		group.createStudentGroup(groupNew);
		StudentGroup groupTest = group.findStudentGroupById(groupNew.getGroupId());
		QueryDataSet qDataSet = new QueryDataSet(iconn);
		qDataSet.addTable("student_group", "SELECT * FROM student_group");
		IDataSet ds = qDataSet;
		ITable table = ds.getTable("student_group");
		Assert.assertEquals(table.getValue(table.getRowCount() - 1, "group_name"), groupTest.getGroupName());
		group.deleteStudentGroup(groupNew);
	}

}
