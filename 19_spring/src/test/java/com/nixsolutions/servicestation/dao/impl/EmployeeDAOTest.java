package com.nixsolutions.servicestation.dao.impl;

import com.nixsolutions.servicestation.entity.Employee;
import com.nixsolutions.servicestation.entity.EmployeeCategory;
import com.nixsolutions.servicestation.service.EmployeeService;
import com.nixsolutions.servicestation.util.AppConfig;
import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.FileInputStream;
import java.util.Set;

/**
 * Created by rybkinrolla on 05.01.2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class)
public class EmployeeDAOTest extends DBTestCase {
    @Autowired
    AbstractApplicationContext context;

    @Autowired
    private EmployeeService employeeService;

    private JdbcDatabaseTester tester;

    public EmployeeDAOTest() {
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

    @Before
    public void setUp() throws Exception {
        tester = new JdbcDatabaseTester("org.h2.Driver",
                "jdbc:h2:mem:sqllab", "root", "root");
        tester.setDataSet(getDataSet());
        employeeService = (EmployeeService) context.getBean("employeeService");
    }

    @Test
    public void testCreate() throws Exception {
        Employee employee = new Employee();
        employee.setFirstName("Steve");
        employee.setLastName("Stevenson");
        EmployeeCategory employeeCategory = new EmployeeCategory();
        employeeCategory.setEmployeeCategoryId(2L);
        employee.setEmployeeCategory(employeeCategory);
        employeeService.create(employee);
        IDataSet expectedResult = new FlatXmlDataFileLoader().load("/Employee/create.xml");
        IDataSet actualResult = tester.getConnection().createDataSet();
        ITable table = DefaultColumnFilter.includedColumnsTable(actualResult.getTable("employee"),
                expectedResult.getTableMetaData("employee").getColumns());
        Assertion.assertEquals(expectedResult.getTable("employee"), table);
    }

    @Test
    public void testDelete() throws Exception {
        Employee employee = employeeService.findById(5L);
        employeeService.delete(employee);
        IDataSet expectedResult = new FlatXmlDataFileLoader().load("/Employee/delete.xml");
        IDataSet actualResult = tester.getConnection().createDataSet();
        ITable table = DefaultColumnFilter.includedColumnsTable(actualResult.getTable("employee"),
                expectedResult.getTableMetaData("employee").getColumns());
        Assertion.assertEquals(expectedResult.getTable("employee"), table);
    }

    @Test
    public void testUpdate() throws Exception {
        Employee employee = employeeService.findById(6L);
        employee.setLastName("S");
        employeeService.update(employee);
        IDataSet expectedResult = new FlatXmlDataFileLoader().load("/Employee/update.xml");
        IDataSet actualResult = tester.getConnection().createDataSet();
        ITable table = DefaultColumnFilter.includedColumnsTable(actualResult.getTable("employee"),
                expectedResult.getTableMetaData("employee").getColumns());
        Assertion.assertEquals(expectedResult.getTable("employee"), table);
    }

    @Test
    public void testFindById() throws Exception {
        Employee employee = employeeService.findById(1L);
        Assert.assertEquals("Vit", employee.getFirstName());
        Assert.assertEquals("Ryb", employee.getLastName());
        Assert.assertEquals(new Long(1), employee.getEmployeeCategory().getEmployeeCategoryId());
    }

    @Test
    public void testFindAll() throws Exception {
        Set<Employee> employeeList = employeeService.findAll();
        Assert.assertEquals(5, employeeList.size());
    }
}
