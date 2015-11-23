package com.nixsolutions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.entity.Status;
import com.nixsolutions.dao.impl.StatusDaoImpl;
import com.nixsolutions.util.ConnectionManager;

public class StatusTest {
    private static Connection conn;

    @BeforeClass
    public static void before() throws ClassNotFoundException, IOException, SQLException {
	conn = ConnectionManager.getConnection();
    }

    @AfterClass
    public static void after() throws ClassNotFoundException, IOException, SQLException {
	conn.close();
    }

    @Test
    public void tableSizesThrougDAOAndDBUnitShouldBeEqual()
	    throws ClassNotFoundException, IOException, SQLException, DatabaseUnitException {
	StatusDaoImpl s = DaoFactory.getStatus();
	List<Status> statusList = s.getAll();
	IDatabaseConnection iconn = new DatabaseConnection(conn);
	QueryDataSet qDataSet = new QueryDataSet(iconn);
	qDataSet.addTable("status", "SELECT * FROM status");
	IDataSet ds = qDataSet;
	ITable table = ds.getTable("status");
	Assert.assertTrue(table.getRowCount() == statusList.size());
    }

    @Test
    public void bothTablesShouldHaveTheSameContent()
	    throws DatabaseUnitException, ClassNotFoundException, IOException, SQLException {
	StatusDaoImpl s = DaoFactory.getStatus();
	List<Status> statusList = s.getAll();
	IDatabaseConnection iconn = new DatabaseConnection(conn);
	QueryDataSet qDataSet = new QueryDataSet(iconn);
	qDataSet.addTable("status", "SELECT * FROM status");
	IDataSet ds = qDataSet;
	ITable table = ds.getTable("status");
	for (int i = 0; i < statusList.size(); i++) {
	    Assert.assertEquals(statusList.get(i).getStatusId(), table.getValue(i, "status_id"));
	    Assert.assertEquals(statusList.get(i).getStatusName(), table.getValue(i, "status_name"));
	}
    }
}
