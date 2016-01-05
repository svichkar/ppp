package com.nixsolutions.servicestation.dao.impl;

import com.nixsolutions.servicestation.dao.EmployeeCategoryDAO;
import com.nixsolutions.servicestation.entity.EmployeeCategory;
import com.nixsolutions.servicestation.util.TestJointUtil;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Assert;


import java.util.List;

/**
 * Created by rybkinrolla on 05.01.2016.
 */
public class TestEmployeeCategoryDAO extends TestJointUtil{
    private EmployeeCategory employeeCategory;

    public TestEmployeeCategoryDAO(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
        preCondition = new FlatXmlDataFileLoader().load("/EmployeeCategory/initial.xml");
        tester.setDataSet(preCondition);
        tester.onSetup();
    }

    public void testCreate() throws Exception {
        employeeCategory = new EmployeeCategory("tuning");
        EmployeeCategoryDAO employeeCategoryDAO = factoryDAO.getEmployeeCategoryDAO();
        employeeCategoryDAO.create(employeeCategory);
        IDataSet expectedResult = new FlatXmlDataFileLoader().load("/EmployeeCategory/create.xml");
        IDataSet actualResult = tester.getConnection().createDataSet();
        ITable table = DefaultColumnFilter.includedColumnsTable(actualResult.getTable("employee_category"),
                expectedResult.getTableMetaData("employee_category").getColumns());
        Assertion.assertEquals(expectedResult.getTable("employee_category"), table);
    }

    public void testDelete() throws Exception {
        employeeCategory = new EmployeeCategory("engine");
        employeeCategory.setEmployeeCategoryId(101);
        EmployeeCategoryDAO employeeCategoryDAO = factoryDAO.getEmployeeCategoryDAO();
        employeeCategoryDAO.delete(employeeCategory);
        IDataSet expectedResult = new FlatXmlDataFileLoader().load("/EmployeeCategory/delete.xml");
        IDataSet actualResult = tester.getConnection().createDataSet();
        ITable table = DefaultColumnFilter.includedColumnsTable(actualResult.getTable("employee_category"),
                expectedResult.getTableMetaData("employee_category").getColumns());
        Assertion.assertEquals(expectedResult.getTable("employee_category"), table);
    }

    public void testUpdate() throws Exception {
        employeeCategory = new EmployeeCategory("transmission");
        employeeCategory.setEmployeeCategoryId(100);
        EmployeeCategoryDAO employeeCategoryDAO = factoryDAO.getEmployeeCategoryDAO();
        employeeCategoryDAO.update(employeeCategory);
        IDataSet expectedResult = new FlatXmlDataFileLoader().load("/EmployeeCategory/update.xml");
        IDataSet actualResult = tester.getConnection().createDataSet();
        ITable table = DefaultColumnFilter.includedColumnsTable(actualResult.getTable("employee_category"),
                expectedResult.getTableMetaData("employee_category").getColumns());
        Assertion.assertEquals(expectedResult.getTable("employee_category"), table);
    }

    public void testFindById() throws Exception {
        EmployeeCategoryDAO employeeCategoryDAO = factoryDAO.getEmployeeCategoryDAO();
        employeeCategory = employeeCategoryDAO.findById(100);
        Assert.assertEquals("electricity", employeeCategory.getName());
    }

    public void testFindAll() throws Exception {
        EmployeeCategoryDAO employeeCategoryDAO = factoryDAO.getEmployeeCategoryDAO();
        List<EmployeeCategory> employeeCategoryList = employeeCategoryDAO.findAll();
        Assert.assertEquals(2,employeeCategoryList.size());
        Assert.assertEquals("electricity", employeeCategoryList.get(0).getName());
        Assert.assertEquals("engine", employeeCategoryList.get(1).getName());
    }
}
