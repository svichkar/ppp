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
import com.nixsolutions.entity.Term;
import com.nixsolutions.dao.impl.TermDaoImpl;
import com.nixsolutions.util.ConnectionManager;

public class TermTest {
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
	TermDaoImpl t = DaoFactory.getTerm();
	List<Term> termList = t.getAll();
	IDatabaseConnection iconn = new DatabaseConnection(conn);
	QueryDataSet qDataSet = new QueryDataSet(iconn);
	qDataSet.addTable("term", "SELECT * FROM term");
	IDataSet ds = qDataSet;
	ITable table = ds.getTable("term");
	Assert.assertTrue(table.getRowCount() == termList.size());
    }

    @Test
    public void bothTablesShouldHaveTheSameContent()
	    throws DatabaseUnitException, ClassNotFoundException, IOException, SQLException {
	TermDaoImpl t = DaoFactory.getTerm();
	List<Term> termList = t.getAll();
	IDatabaseConnection iconn = new DatabaseConnection(conn);
	QueryDataSet qDataSet = new QueryDataSet(iconn);
	qDataSet.addTable("term", "SELECT * FROM term");
	IDataSet ds = qDataSet;
	ITable table = ds.getTable("term");
	for (int i = 0; i < termList.size(); i++) {
	    Assert.assertEquals(termList.get(i).getTermId(), table.getValue(i, "term_id"));
	    Assert.assertEquals(termList.get(i).getTermAlias(), table.getValue(i, "term_alias"));
	}
    }
}
