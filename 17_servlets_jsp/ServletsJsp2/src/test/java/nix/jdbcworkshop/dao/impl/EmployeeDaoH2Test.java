/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.jdbcworkshop.dao.impl;

import java.util.List;
import nix.jdbcworkshop.entities.Employee;
import org.dbunit.Assertion;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;

/**
 *
 * @author mednorcom
 */
public class EmployeeDaoH2Test extends AbstractDaoH2Test {

    private EmployeeDaoH2 employeeDaoInstance;

    public EmployeeDaoH2Test() {
        super();
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new CompositeDataSet(new IDataSet[]{
            new FlatXmlDataSet(getClass().getClassLoader()
            .getResourceAsStream(dbunitConfig.getString("dbunit.web.role.initial"))),
            new FlatXmlDataSet(getClass().getClassLoader()
            .getResourceAsStream(dbunitConfig.getString("dbunit.web.user.initial"))),
            new FlatXmlDataSet(getClass().getClassLoader()
            .getResourceAsStream(dbunitConfig.getString("dbunit.employee.category.initial"))),
            new FlatXmlDataSet(getClass().getClassLoader()
            .getResourceAsStream(dbunitConfig.getString("dbunit.employee.initial")))
        });
    }

    public void setUp() throws Exception {
        super.setUp();
        employeeDaoInstance = new EmployeeDaoH2();
    }

    /**
     * Test of create method, of class EmployeeDaoH2.
     */
    public void testCreate() throws Exception {
        Employee employee = new Employee(null, "Name6", "Sname6", (short) 3, null);
        employeeDaoInstance.create(employee);
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("employee");
        IDataSet expectedDataSet = new FlatXmlDataSet(getClass().getClassLoader()
                .getResourceAsStream(dbunitConfig.getString("dbunit.employee.expected.create")));
        ITable expectedTable = expectedDataSet.getTable("employee");
        Assertion.assertEquals(expectedTable, DefaultColumnFilter.includedColumnsTable(
                actualTable, expectedTable.getTableMetaData().getColumns()));
        assertNotNull(employee.getEmployeeId());
    }

    /**
     * Test of update method, of class EmployeeDaoH2.
     */
    public void testUpdate() throws Exception {
        Employee employee = new Employee(null, "Name6", "Sname6", (short) 3, null);
        employeeDaoInstance.create(employee);
        employee.setEmployeeCategoryId((short) 1);
        employeeDaoInstance.update(employee);
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("employee");
        IDataSet expectedDataSet = new FlatXmlDataSet(getClass().getClassLoader()
                .getResourceAsStream(dbunitConfig.getString("dbunit.employee.expected.update")));
        ITable expectedTable = expectedDataSet.getTable("employee");
        Assertion.assertEquals(expectedTable, DefaultColumnFilter.includedColumnsTable(
                actualTable, expectedTable.getTableMetaData().getColumns()));
    }

    /**
     * Test of delete method, of class EmployeeDaoH2.
     */
    public void testDelete() throws Exception {
        Employee employee = new Employee(null, "Name6", "Sname6", (short) 3, null);
        employeeDaoInstance.create(employee);
        employeeDaoInstance.delete(employee);
        employeeDaoInstance.delete(new Employee((long) 5, null, null, null, null));
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("employee");
        IDataSet expectedDataSet = new FlatXmlDataSet(getClass().getClassLoader()
                .getResourceAsStream(dbunitConfig.getString("dbunit.employee.expected.delete")));
        ITable expectedTable = expectedDataSet.getTable("employee");
        Assertion.assertEquals(expectedTable, DefaultColumnFilter.includedColumnsTable(
                actualTable, expectedTable.getTableMetaData().getColumns()));
    }

    /**
     * Test of findCarById method, of class EmployeeDaoH2.
     */
    public void testFindEmployeeById() {
        Employee employee = employeeDaoInstance.findEmployeeById(5);
        assertEquals(new Long(5), employee.getEmployeeId());
        assertEquals("Name5", employee.getFirstName());
        assertEquals("Sname5", employee.getLastName());
        assertEquals(new Short((short) 2), employee.getEmployeeCategoryId());
    }

    public void testGetEmployeeList() {
        List<Employee> employeeList = employeeDaoInstance.getEmployeeList();
        assertEquals(5, employeeList.size());
    }

    public void testGetEmployeeListLimit() {
        List<Employee> employeeListLimit = employeeDaoInstance.getEmployeeList(4);
        assertEquals(4, employeeListLimit.size());
        assertEquals(new Long(1), employeeListLimit.get(0).getEmployeeId());
        assertEquals(new Long(4), employeeListLimit.get(3).getEmployeeId());
    }

    public void testGetEmployeeListOffset() {
        List<Employee> employeeListOffset = employeeDaoInstance.getEmployeeList(1, 4);
        assertEquals(4, employeeListOffset.size());
        assertEquals(new Long(2), employeeListOffset.get(0).getEmployeeId());
        assertEquals(new Long(5), employeeListOffset.get(3).getEmployeeId());
    }

}
