package com.nixsolutions.servicestation.dao.impl;


import com.nixsolutions.servicestation.dao.EmployeeDAO;
import com.nixsolutions.servicestation.entity.Employee;
import com.nixsolutions.servicestation.entity.EmployeeCategory;
import org.dbunit.Assertion;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Assert;

import java.io.FileInputStream;

/**
 * Created by rybkinrolla on 05.01.2016.
 */
public class TestEmployeeDAO extends TestJointUtil {
    private Employee employee;

    public TestEmployeeDAO(String name) {
        super(name);
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.h2.Driver");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:h2:mem:sqllab3");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "root");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "root");
    }
    @Override
    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.DELETE_ALL;
    }

    @Override
    protected DatabaseOperation getSetUpOperation() throws Exception {
        return DatabaseOperation.CLEAN_INSERT;
    }
    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().setColumnSensing(true).build(new FileInputStream("src/test/resources/Employee/initial.xml"));
    }

    public void setUp() throws Exception {
        tester = new JdbcDatabaseTester("org.h2.Driver",
                "jdbc:h2:mem:sqllab3", "root", "root");
        tester.setDataSet(getDataSet());
    }

    public void testCreate() throws Exception {
        employee = new Employee();
        employee.setFirstName("Steve");
        employee.setLastName("Stevenson");
        EmployeeCategory employeeCategory = new EmployeeCategory();
        employeeCategory.setEmployeeCategoryId(2L);
        employee.setEmployeeCategory(employeeCategory);
        factoryDAO.getEmployeeDAO().create(employee);
        IDataSet expectedResult = new FlatXmlDataFileLoader().load("/Employee/create.xml");
        IDataSet actualResult = tester.getConnection().createDataSet();
        ITable table = DefaultColumnFilter.includedColumnsTable(actualResult.getTable("employee"),
                expectedResult.getTableMetaData("employee").getColumns());
        Assertion.assertEquals(expectedResult.getTable("employee"), table);
    }

    /*public void testDelete() throws Exception {
        employee = new Employee ("Jackson","Jackson",2);
        employee.setEmployeeId(101);
        EmployeeDAO employeeDAO = factoryDAO.getEmployeeDAO();
        employeeDAO.delete(employee);
        IDataSet expectedResult = new FlatXmlDataFileLoader().load("/Employee/delete.xml");
        IDataSet actualResult = tester.getConnection().createDataSet();
        ITable table = DefaultColumnFilter.includedColumnsTable(actualResult.getTable("employee"),
                expectedResult.getTableMetaData("employee").getColumns());
        Assertion.assertEquals(expectedResult.getTable("employee"), table);
    }

    public void testUpdate() throws Exception {
        employee = new Employee ("Michael","Jackson",2);
        employee.setEmployeeId(101);
        EmployeeDAO employeeDAO = factoryDAO.getEmployeeDAO();
        employeeDAO.update(employee);
        IDataSet expectedResult = new FlatXmlDataFileLoader().load("/Employee/update.xml");
        IDataSet actualResult = tester.getConnection().createDataSet();
        ITable table = DefaultColumnFilter.includedColumnsTable(actualResult.getTable("employee"),
                expectedResult.getTableMetaData("employee").getColumns());
        Assertion.assertEquals(expectedResult.getTable("employee"), table);
    }

    public void testFindById() throws Exception {
        EmployeeDAO employeeDAO = factoryDAO.getEmployeeDAO();
        employee = employeeDAO.findById(100);
        Assert.assertEquals("John", employee.getFirstName());
        Assert.assertEquals("Johnson", employee.getLastName());
        Assert.assertEquals(new Integer(1), employee.getEmployeeCategoryId());
    }

    public void testFindAll() throws Exception {
        EmployeeDAO employeeDAO = factoryDAO.getEmployeeDAO();
        List<Employee> employeeList = employeeDAO.findAll();
        Assert.assertEquals(2,employeeList.size());
        Assert.assertEquals("John", employeeList.get(0).getFirstName());
        Assert.assertEquals("Johnson", employeeList.get(0).getLastName());
        Assert.assertEquals(new Integer(1), employeeList.get(0).getEmployeeCategoryId());
        Assert.assertEquals("Jack", employeeList.get(1).getFirstName());
        Assert.assertEquals("Jackson", employeeList.get(1).getLastName());
        Assert.assertEquals(new Integer(2), employeeList.get(1).getEmployeeCategoryId());
    }*/
}
