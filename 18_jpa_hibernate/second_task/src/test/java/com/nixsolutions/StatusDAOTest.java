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
import com.nixsolutions.studentgrade.dao.StatusDAO;
import com.nixsolutions.studentgrade.entity.Status;
import com.nixsolutions.studentgrade.util.ConnectionManager;

public class StatusDAOTest {
	private static Connection conn;
	private static IDatabaseConnection iconn;
	private static StatusDAO status;

	@BeforeClass
	public static void setUp() throws DatabaseUnitException {
		conn = ConnectionManager.getConnection();
		iconn = new DatabaseConnection(conn);
		status = DAOFactory.getStatus();
		DAOApp.main(null);
	}

	@AfterClass
	public static void tearDown() throws SQLException {
		conn.close();
		iconn.close();
	}

	@Test
	public void shouldCreateStatus() throws DataSetException {
		Status statusNew = new Status();
		statusNew.setStatusName("Non-active");
		status.createStatus(statusNew);
		QueryDataSet qDataSet = new QueryDataSet(iconn);
		qDataSet.addTable("status", "SELECT * FROM status");
		IDataSet ds = qDataSet;
		ITable table = ds.getTable("status");
		Assert.assertEquals(statusNew.getStatusName(), table.getValue(table.getRowCount() - 1, "status_name"));
		status.deleteStatus(statusNew);
	}

	@Test
	public void shouldUpdateStatus() throws DataSetException {
		Status statusUpdate = new Status();
		statusUpdate.setStatusName("Non-active");
		status.createStatus(statusUpdate);
		statusUpdate.setStatusName("Non-active Test");
		status.updateStatus(statusUpdate);
		QueryDataSet qDataSet = new QueryDataSet(iconn);
		qDataSet.addTable("status", "SELECT * FROM status");
		IDataSet ds = qDataSet;
		ITable table = ds.getTable("status");
		Assert.assertEquals(statusUpdate.getStatusName(), table.getValue(table.getRowCount() - 1, "status_name"));
		status.deleteStatus(statusUpdate);
	}

	@Test
	public void shouldDeleteStatus() throws DataSetException {
		Status statusDelete = new Status();
		statusDelete.setStatusName("Non-active");
		status.createStatus(statusDelete);
		QueryDataSet qDataSetBefore = new QueryDataSet(iconn);
		qDataSetBefore.addTable("status", "SELECT * FROM status");
		IDataSet dataSetBefore = qDataSetBefore;
		ITable tableBefore = dataSetBefore.getTable("status");
		int sizeBefore = tableBefore.getRowCount();
		status.deleteStatus(statusDelete);
		QueryDataSet qDataSetAfter = new QueryDataSet(iconn);
		qDataSetAfter.addTable("status", "SELECT * FROM status");
		IDataSet dataSetAfter = qDataSetAfter;
		ITable tableAfter = dataSetAfter.getTable("status");
		int sizeAfter = tableAfter.getRowCount();
		Assert.assertEquals(sizeBefore - 1, sizeAfter);
	}

	@Test
	public void shouldGetAllStatuses() throws DataSetException {
		List<Status> statuses = status.findAllStatuses();
		QueryDataSet qDataSet = new QueryDataSet(iconn);
		qDataSet.addTable("status", "SELECT * FROM status");
		IDataSet ds = qDataSet;
		ITable table = ds.getTable("status");
		Assert.assertEquals(table.getRowCount(), statuses.size());
	}

	@Test
	public void shouldFindStatusById() throws DataSetException {
		Status statusNew = new Status();
		statusNew.setStatusName("Non-active");
		status.createStatus(statusNew);
		Status statusTest = status.findStatusById(statusNew.getStatusId());
		QueryDataSet qDataSet = new QueryDataSet(iconn);
		qDataSet.addTable("status", "SELECT * FROM status");
		IDataSet ds = qDataSet;
		ITable table = ds.getTable("status");
		Assert.assertEquals(table.getValue(table.getRowCount() - 1, "status_name"), statusTest.getStatusName());
		status.deleteStatus(statusNew);
	}
}
