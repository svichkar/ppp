/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.jdbcworkshop.dao.impl;

import java.util.List;
import nix.jdbcworkshop.entities.EmployeeCategory;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;

/**
 *
 * @author mednorcom
 */
public class EmployeeCategoryDaoH2Test extends AbstractDaoH2Test {

    private EmployeeCategoryDaoH2 employeeCategoryDaoInstance;

    public EmployeeCategoryDaoH2Test() {
        super();
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSet(
                getClass().getClassLoader().getResourceAsStream(dbunitConfig.getString("dbunit.employee.category.initial")));
    }

    public void setUp() throws Exception {
        super.setUp();
        employeeCategoryDaoInstance = new EmployeeCategoryDaoH2();
    }

    /**
     * Test of create method, of class EmployeeCategoryDaoH2.
     */
    public void testCreate() throws Exception {
        EmployeeCategory employeeCategory = new EmployeeCategory(null, "Paint");
        employeeCategoryDaoInstance.create(employeeCategory);
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("employee_category");
        IDataSet expectedDataSet = new FlatXmlDataSet(getClass().getClassLoader().getResourceAsStream(dbunitConfig.getString("dbunit.employee.category.expected.create")));
        ITable expectedTable = expectedDataSet.getTable("employee_category");
        Assertion.assertEquals(expectedTable, DefaultColumnFilter.includedColumnsTable(actualTable, expectedTable.getTableMetaData().getColumns()));
        assertNotNull(employeeCategory.getEmployeeCategoryId());
    }

    /**
     * Test of update method, of class EmployeeCategoryDaoH2.
     */
    public void testUpdate() throws Exception {
        EmployeeCategory employeeCategory = new EmployeeCategory(null, "Paint");
        employeeCategoryDaoInstance.create(employeeCategory);
        employeeCategory.setName("Paint Job");
        employeeCategoryDaoInstance.update(employeeCategory);
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("employee_category");
        IDataSet expectedDataSet = new FlatXmlDataSet(getClass().getClassLoader().getResourceAsStream(dbunitConfig.getString("dbunit.employee.category.expected.update")));
        ITable expectedTable = expectedDataSet.getTable("employee_category");
        Assertion.assertEquals(expectedTable, DefaultColumnFilter.includedColumnsTable(actualTable, expectedTable.getTableMetaData().getColumns()));
        //employeeCategoryDaoInstance.delete(employeeCategory);
    }

    /**
     * Test of delete method, of class EmployeeCategoryDaoH2.
     */
    public void testDelete() throws Exception {
        EmployeeCategory employeeCategory = new EmployeeCategory(null, "Paint");
        employeeCategoryDaoInstance.create(employeeCategory);
        employeeCategoryDaoInstance.delete(employeeCategory);
        employeeCategoryDaoInstance.delete(new EmployeeCategory((short)3, null));
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("employee_category");
        IDataSet expectedDataSet = new FlatXmlDataSet(getClass().getClassLoader().getResourceAsStream(dbunitConfig.getString("dbunit.employee.category.expected.delete")));
        ITable expectedTable = expectedDataSet.getTable("employee_category");
        Assertion.assertEquals(expectedTable, DefaultColumnFilter.includedColumnsTable(actualTable, expectedTable.getTableMetaData().getColumns()));
    }

    /**
     * Test of findCarById method, of class EmployeeCategoryDaoH2.
     */
    public void testFindEmployeeCategoryById() {
        EmployeeCategory employeeCategory = employeeCategoryDaoInstance.findEmployeeCategoryById(3);
        assertEquals(new Short((short)3), employeeCategory.getEmployeeCategoryId());
        assertEquals("Transmission", employeeCategory.getName());
    }

     public void testGetEmployeeCategoryList() {
        List<EmployeeCategory> employeeCategoryList = employeeCategoryDaoInstance.getEmployeeCategoryList();
        assertEquals(3, employeeCategoryList.size());       
    }
     
      public void testGetEmployeeCategoryListLimit() {
        List<EmployeeCategory> employeeCategoryListLimit = employeeCategoryDaoInstance.getEmployeeCategoryList(2);    
        assertEquals(2, employeeCategoryListLimit.size());
        assertEquals(new Short((short)1), employeeCategoryListLimit.get(0).getEmployeeCategoryId());
        assertEquals(new Short((short)2), employeeCategoryListLimit.get(1).getEmployeeCategoryId());
    }
    
    
    public void testGetEmployeeCategoryListOffset() {
        List<EmployeeCategory> employeeCategoryListOffset = employeeCategoryDaoInstance.getEmployeeCategoryList(1,2);
        assertEquals(2, employeeCategoryListOffset.size());
        assertEquals(new Short((short)2), employeeCategoryListOffset.get(0).getEmployeeCategoryId());
        assertEquals(new Short((short)3), employeeCategoryListOffset.get(1).getEmployeeCategoryId());
    }

}
