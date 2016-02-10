/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.jdbcworkshop.dao.impl;

import java.util.List;
import nix.jdbcworkshop.entities.EmployeeCarOrder;
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
public class EmployeeCarOrderDaoH2Test extends AbstractDaoH2Test {

    private EmployeeCarOrderDaoH2 employeeCarOrderDaoInstance;

    public EmployeeCarOrderDaoH2Test() {
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
            .getResourceAsStream(dbunitConfig.getString("dbunit.car.type.initial"))),
            new FlatXmlDataSet(getClass().getClassLoader()
            .getResourceAsStream(dbunitConfig.getString("dbunit.client.initial"))),
            new FlatXmlDataSet(getClass().getClassLoader()
            .getResourceAsStream(dbunitConfig.getString("dbunit.car.initial"))),
            new FlatXmlDataSet(getClass().getClassLoader()
            .getResourceAsStream(dbunitConfig.getString("dbunit.car.order.status.initial"))),
            new FlatXmlDataSet(getClass().getClassLoader()
            .getResourceAsStream(dbunitConfig.getString("dbunit.car.order.initial"))),
            new FlatXmlDataSet(getClass().getClassLoader()
            .getResourceAsStream(dbunitConfig.getString("dbunit.employee.category.initial"))),
            new FlatXmlDataSet(getClass().getClassLoader()
            .getResourceAsStream(dbunitConfig.getString("dbunit.employee.initial"))),
            new FlatXmlDataSet(getClass().getClassLoader()
            .getResourceAsStream(dbunitConfig.getString("dbunit.employee.car.order.initial"))),});
    }

    public void setUp() throws Exception {
        super.setUp();
        employeeCarOrderDaoInstance = new EmployeeCarOrderDaoH2();
    }

    /**
     * Test of create method, of class EmployeeCarOrderDaoH2.
     */
    public void testCreate() throws Exception {
        EmployeeCarOrder employeeCarOrder = new EmployeeCarOrder(1, 5);
        employeeCarOrderDaoInstance.create(employeeCarOrder);
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("employee_car_order");
        IDataSet expectedDataSet = new FlatXmlDataSet(getClass().getClassLoader()
                .getResourceAsStream(dbunitConfig.getString("dbunit.employee.car.order.expected.create")));
        ITable expectedTable = expectedDataSet.getTable("employee_car_order");
        Assertion.assertEquals(expectedTable, DefaultColumnFilter.includedColumnsTable(
                actualTable, expectedTable.getTableMetaData().getColumns()));
    }

    /**
     * Test of delete method, of class EmployeeCarOrderDaoH2.
     */
    public void testDelete() throws Exception {
        EmployeeCarOrder employeeCarOrder = new EmployeeCarOrder(5, 1);
        employeeCarOrderDaoInstance.create(employeeCarOrder);
        employeeCarOrderDaoInstance.delete(employeeCarOrder);
        employeeCarOrderDaoInstance.delete(new EmployeeCarOrder(5, 5));
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("employee_car_order");
        IDataSet expectedDataSet = new FlatXmlDataSet(getClass().getClassLoader()
                .getResourceAsStream(dbunitConfig
                        .getString("dbunit.employee.car.order.expected.delete")));
        ITable expectedTable = expectedDataSet.getTable("employee_car_order");
        Assertion.assertEquals(expectedTable, DefaultColumnFilter.includedColumnsTable(
                actualTable, expectedTable.getTableMetaData().getColumns()));
    }

    /**
     * Test of findCarById method, of class EmployeeCarOrderDaoH2.
     */
    public void testFindEmployeeCarOrderByCarOrderId() {
        EmployeeCarOrder employeeCarOrder
                = employeeCarOrderDaoInstance.findEmployeeCarOrderByCarOrderId(5);
        assertEquals(5, employeeCarOrder.getCarOrderId());
        assertEquals(5, employeeCarOrder.getEmployeeId());
    }

    public void testFindEmployeeCarOrderByEmployeeId() {
        EmployeeCarOrder employeeCarOrder
                = employeeCarOrderDaoInstance.findEmployeeCarOrderByEmployeeId(5);
        assertEquals(5, employeeCarOrder.getCarOrderId());
        assertEquals(5, employeeCarOrder.getEmployeeId());
    }

    public void testGetEmployeeCarOrderList() {
        List<EmployeeCarOrder> employeeCarOrderList
                = employeeCarOrderDaoInstance.getEmployeeCarOrderList();
        assertEquals(5, employeeCarOrderList.size());
    }

    public void testGetEmployeeCarOrderListLimit() {
        List<EmployeeCarOrder> employeeCarOrderListLimit
                = employeeCarOrderDaoInstance.getEmployeeCarOrderList(4);
        assertEquals(4, employeeCarOrderListLimit.size());
        assertEquals(1, employeeCarOrderListLimit.get(0).getCarOrderId());
        assertEquals(1, employeeCarOrderListLimit.get(0).getCarOrderId());
        assertEquals(4, employeeCarOrderListLimit.get(3).getEmployeeId());
        assertEquals(4, employeeCarOrderListLimit.get(3).getEmployeeId());
    }

    public void testGetEmployeeCarOrderListOffset() {
        List<EmployeeCarOrder> employeeCarOrderListOffset
                = employeeCarOrderDaoInstance.getEmployeeCarOrderList(1, 4);
        assertEquals(4, employeeCarOrderListOffset.size());
        assertEquals(2, employeeCarOrderListOffset.get(0).getCarOrderId());
        assertEquals(2, employeeCarOrderListOffset.get(0).getCarOrderId());
        assertEquals(5, employeeCarOrderListOffset.get(3).getEmployeeId());
        assertEquals(5, employeeCarOrderListOffset.get(3).getEmployeeId());
    }

}
