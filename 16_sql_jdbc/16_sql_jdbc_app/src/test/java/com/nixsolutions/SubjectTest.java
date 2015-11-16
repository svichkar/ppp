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

import com.nixsolutions.dao.DAOFactory;
import com.nixsolutions.dao.Subject;

public class SubjectTest {
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
	Subject s = DAOFactory.getSubject();
	List<Subject> subjectList = s.select(new String[] { "*" }, null);
	IDatabaseConnection iconn = new DatabaseConnection(conn);
	QueryDataSet qDataSet = new QueryDataSet(iconn);
	qDataSet.addTable("subject", "SELECT * FROM subject");
	IDataSet ds = qDataSet;
	ITable table = ds.getTable("subject");
	Assert.assertTrue(table.getRowCount() == subjectList.size());
    }

    @Test
    public void bothTablesShouldHaveTheSameContent()
	    throws DatabaseUnitException, ClassNotFoundException, IOException, SQLException {
	Subject s = DAOFactory.getSubject();
	List<Subject> subjectList = s.select(new String[] { "*" }, null);
	IDatabaseConnection iconn = new DatabaseConnection(conn);
	QueryDataSet qDataSet = new QueryDataSet(iconn);
	qDataSet.addTable("subject", "SELECT * FROM subject");
	IDataSet ds = qDataSet;
	ITable table = ds.getTable("subject");
	for (int i = 0; i < subjectList.size(); i++) {
	    Assert.assertEquals(subjectList.get(i).getSubjectId(), table.getValue(i, "subject_id"));
	    Assert.assertEquals(subjectList.get(i).getSubjectName(), table.getValue(i, "subject_name"));
	    Assert.assertEquals(subjectList.get(i).getTermId(), table.getValue(i, "term_id"));
	}
    }
}
