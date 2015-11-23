package com.nixsolutions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.entity.Student;
import com.nixsolutions.dao.impl.StudentDaoImpl;
import com.nixsolutions.util.ConnectionManager;

public class StudentTest {
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
	StudentDaoImpl s = DaoFactory.getStudent();
	List<Student> studentList = s.getAll();
	IDatabaseConnection iconn = new DatabaseConnection(conn);
	QueryDataSet qDataSet = new QueryDataSet(iconn);
	qDataSet.addTable("student", "SELECT * FROM student");
	IDataSet ds = qDataSet;
	ITable table = ds.getTable("student");
	Assert.assertTrue(table.getRowCount() == studentList.size());
    }

    @Test
    public void bothTablesShouldHaveTheSameContent()
	    throws DatabaseUnitException, ClassNotFoundException, IOException, SQLException {
	StudentDaoImpl s = DaoFactory.getStudent();
	List<Student> studentList = s.getAll();
	IDatabaseConnection iconn = new DatabaseConnection(conn);
	QueryDataSet qDataSet = new QueryDataSet(iconn);
	qDataSet.addTable("student", "SELECT * FROM student");
	IDataSet ds = qDataSet;
	ITable table = ds.getTable("student");
	for (int i = 0; i < studentList.size(); i++) {
	    Assert.assertEquals(studentList.get(i).getStudentId(), table.getValue(i, "student_id"));
	    Assert.assertEquals(studentList.get(i).getFirstName(), table.getValue(i, "first_name"));
	    Assert.assertEquals(studentList.get(i).getLastName(), table.getValue(i, "last_name"));
	    Assert.assertEquals(studentList.get(i).getDateBirthday(), table.getValue(i, "date_birthday"));
	    Assert.assertEquals(studentList.get(i).getDateEntry(), table.getValue(i, "date_entry"));
	    Assert.assertEquals(studentList.get(i).getTermId(), table.getValue(i, "term_id"));
	    Assert.assertEquals(studentList.get(i).getStudentGroupId(), table.getValue(i, "student_group_id"));
	    Assert.assertEquals(studentList.get(i).getStatusId(), table.getValue(i, "status_id"));
	}
    }
}
