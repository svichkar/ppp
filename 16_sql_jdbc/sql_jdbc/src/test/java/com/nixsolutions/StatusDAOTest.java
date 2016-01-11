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
import com.nixsolutions.dao.StatusDAO;
import com.nixsolutions.entity.Status;
import com.nixsolutions.util.ConnectionManager;

public class StatusDAOTest {
	private Connection conn;
	private IDatabaseConnection iconn;
	private StatusDAO status;

	@Before
	public void setUp() throws DatabaseUnitException {
		conn = ConnectionManager.getConnection();
		iconn = new DatabaseConnection(conn);
		status = DAOFactory.getStatus();
	}

	@After
	public void tearDown() throws SQLException {
		conn.close();
		iconn.close();
	}

	@Test
	public void shouldCreateStatus() throws DataSetException {
		Status statusNew = new Status(4, "Non-active");
		status.createStatus(statusNew);
		QueryDataSet qDataSet = new QueryDataSet(iconn);
		qDataSet.addTable("status", "SELECT * FROM status");
		IDataSet ds = qDataSet;
		ITable table = ds.getTable("status");
		Assert.assertEquals(statusNew.getStatusName(), table.getValue(statusNew.getStatusId() - 1, "status_name"));
		status.deleteStatus(statusNew);
	}

	@Test
	public void shouldUpdateStatus() throws DataSetException {
		status.createStatus(new Status(4, "Non-active"));
		Status statusUpdate = new Status(4, "Non-active Test");
		status.updateStatus(statusUpdate);
		QueryDataSet qDataSet = new QueryDataSet(iconn);
		qDataSet.addTable("status", "SELECT * FROM status");
		IDataSet ds = qDataSet;
		ITable table = ds.getTable("status");
		Assert.assertEquals(statusUpdate.getStatusName(),
				table.getValue(statusUpdate.getStatusId() - 1, "status_name"));
		status.deleteStatus(statusUpdate);
	}

	@Test
	public void shouldDeleteStatus() throws DataSetException {
		Status statusDelete = new Status(4, "Non-active");
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
		List<Status> statuss = status.findAllStatuses();
		QueryDataSet qDataSet = new QueryDataSet(iconn);
		qDataSet.addTable("status", "SELECT * FROM status");
		IDataSet ds = qDataSet;
		ITable table = ds.getTable("status");
		Assert.assertEquals(table.getRowCount(), statuss.size());
	}

	@Test
	public void shouldFindStatusById() throws DataSetException {
		Status statusTest = status.findStatusById(3);
		QueryDataSet qDataSet = new QueryDataSet(iconn);
		qDataSet.addTable("status", "SELECT * FROM status");
		IDataSet ds = qDataSet;
		ITable table = ds.getTable("status");
		Assert.assertEquals(table.getValue(statusTest.getStatusId() - 1, "status_name"), statusTest.getStatusName());
	}
}
