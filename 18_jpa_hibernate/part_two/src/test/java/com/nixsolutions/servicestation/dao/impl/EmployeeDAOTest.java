package com.nixsolutions.servicestation.dao.impl;


import com.nixsolutions.servicestation.dao.EmployeeCategoryDAO;
import com.nixsolutions.servicestation.dao.EmployeeDAO;
import com.nixsolutions.servicestation.entity.Employee;
import com.nixsolutions.servicestation.entity.EmployeeCategory;
import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
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
import java.util.Set;

/**
 * Created by rybkinrolla on 05.01.2016.
 */
public class EmployeeDAOTest extends DBTestCase {
    private EmployeeDAO employeeDAO;
    private EmployeeCategoryDAO employeeCategoryDAO;
    private JdbcDatabaseTester tester;

    public EmployeeDAOTest(String name) {
        super(name);
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.h2.Driver");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:h2:mem:sqllab");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "root");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "root");
    }
    @Override
    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.NONE;
    }

    @Override
    protected DatabaseOperation getSetUpOperation() throws Exception {
        return DatabaseOperation.NONE;
    }
    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().setColumnSensing(true).build(new FileInputStream("src/test/resources/Employee/initial.xml"));
    }

    public void setUp() throws Exception {
        tester = new JdbcDatabaseTester("org.h2.Driver",
                "jdbc:h2:mem:sqllab", "root", "root");
        tester.setDataSet(getDataSet());
    }

    public void testCreate() throws Exception {
        Employee employee = new Employee();
        employee.setFirstName("Steve");
        employee.setLastName("Stevenson");
        EmployeeCategory employeeCategory = new EmployeeCategory();
        employeeCategory.setEmployeeCategoryId(2L);
        employee.setEmployeeCategory(employeeCategory);
        new FactoryDAOImpl().getEmployeeDAO().create(employee);
        IDataSet expectedResult = new FlatXmlDataFileLoader().load("/Employee/create.xml");
        IDataSet actualResult = tester.getConnection().createDataSet();
        ITable table = DefaultColumnFilter.includedColumnsTable(actualResult.getTable("employee"),
                expectedResult.getTableMetaData("employee").getColumns());
        Assertion.assertEquals(expectedResult.getTable("employee"), table);
    }

    public void testDelete() throws Exception {
        Employee employee = new FactoryDAOImpl().getEmployeeDAO().findById(5L);
        new FactoryDAOImpl().getEmployeeDAO().delete(employee);
        IDataSet expectedResult = new FlatXmlDataFileLoader().load("/Employee/delete.xml");
        IDataSet actualResult = tester.getConnection().createDataSet();
        ITable table = DefaultColumnFilter.includedColumnsTable(actualResult.getTable("employee"),
                expectedResult.getTableMetaData("employee").getColumns());
        Assertion.assertEquals(expectedResult.getTable("employee"), table);
    }

    public void testUpdate() throws Exception {
        Employee employee = new FactoryDAOImpl().getEmployeeDAO().findById(6L);
        employee.setLastName("S");
        new FactoryDAOImpl().getEmployeeDAO().update(employee);
        IDataSet expectedResult = new FlatXmlDataFileLoader().load("/Employee/update.xml");
        IDataSet actualResult = tester.getConnection().createDataSet();
        ITable table = DefaultColumnFilter.includedColumnsTable(actualResult.getTable("employee"),
                expectedResult.getTableMetaData("employee").getColumns());
        Assertion.assertEquals(expectedResult.getTable("employee"), table);
    }

    public void testFindById() throws Exception {
        Employee employee = new FactoryDAOImpl().getEmployeeDAO().findById(1L);
        Assert.assertEquals("Vit", employee.getFirstName());
        Assert.assertEquals("Ryb", employee.getLastName());
        Assert.assertEquals(new Long(1), employee.getEmployeeCategory().getEmployeeCategoryId());
    }

    public void testFindAll() throws Exception {
        Set<Employee> employeeList = new FactoryDAOImpl().getEmployeeDAO().findAll();
        Assert.assertEquals(5,employeeList.size());
    }
}
