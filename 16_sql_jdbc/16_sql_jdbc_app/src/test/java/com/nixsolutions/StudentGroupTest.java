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
import com.nixsolutions.entity.StudentGroup;
import com.nixsolutions.dao.impl.StudentGroupDaoImpl;
import com.nixsolutions.util.ConnectionManager;

public class StudentGroupTest {
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
	StudentGroupDaoImpl s = DaoFactory.getStudentGroup();
	List<StudentGroup> studentGroupList = s.getAll();
	IDatabaseConnection iconn = new DatabaseConnection(conn);
	QueryDataSet qDataSet = new QueryDataSet(iconn);
	qDataSet.addTable("studentgroup", "SELECT * FROM studentgroup");
	IDataSet ds = qDataSet;
	ITable table = ds.getTable("studentgroup");
	Assert.assertTrue(table.getRowCount() == studentGroupList.size());
    }

    @Test
    public void bothTablesShouldHaveTheSameContent()
	    throws DatabaseUnitException, ClassNotFoundException, IOException, SQLException {
	StudentGroupDaoImpl s = DaoFactory.getStudentGroup();
	List<StudentGroup> studentGroupList = s.getAll();
	IDatabaseConnection iconn = new DatabaseConnection(conn);
	QueryDataSet qDataSet = new QueryDataSet(iconn);
	qDataSet.addTable("studentgroup", "SELECT * FROM studentgroup");
	IDataSet ds = qDataSet;
	ITable table = ds.getTable("studentgroup");
	for (int i = 0; i < studentGroupList.size(); i++) {
	    Assert.assertEquals(studentGroupList.get(i).getStudentGroupId(), table.getValue(i, "student_group_id"));
	    Assert.assertEquals(studentGroupList.get(i).getGroupName(), table.getValue(i, "group_name"));
	}
    }
}
