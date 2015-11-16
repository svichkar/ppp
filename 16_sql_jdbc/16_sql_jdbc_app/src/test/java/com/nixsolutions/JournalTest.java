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
import com.nixsolutions.dao.Journal;

public class JournalTest {
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
	Journal j = DAOFactory.getJournal();
	List<Journal> journalList = j.select(new String[] { "*" }, null);
	IDatabaseConnection iconn = new DatabaseConnection(conn);
	QueryDataSet qDataSet = new QueryDataSet(iconn);
	qDataSet.addTable("journal", "SELECT * FROM journal");
	IDataSet ds = qDataSet;
	ITable table = ds.getTable("journal");
	Assert.assertTrue(table.getRowCount() == journalList.size());
    }

    @Test
    public void bothTablesShouldHaveTheSameContent()
	    throws DatabaseUnitException, ClassNotFoundException, IOException, SQLException {
	Journal j = DAOFactory.getJournal();
	List<Journal> journalList = j.select(new String[] { "*" }, null);
	IDatabaseConnection iconn = new DatabaseConnection(conn);
	QueryDataSet qDataSet = new QueryDataSet(iconn);
	qDataSet.addTable("journal", "SELECT * FROM journal");
	IDataSet ds = qDataSet;
	ITable table = ds.getTable("journal");
	for (int i = 0; i < journalList.size(); i++) {
	    Assert.assertEquals(journalList.get(i).getJournalId(), table.getValue(i, "journal_id"));
	    Assert.assertEquals(journalList.get(i).getStudentId(), table.getValue(i, "student_id"));
	    Assert.assertEquals(journalList.get(i).getSubjectId(), table.getValue(i, "subject_id"));
	    Assert.assertEquals(journalList.get(i).getRate(), table.getValue(i, "rate"));
	}
    }
}
