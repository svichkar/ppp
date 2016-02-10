/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.jdbcworkshop.dao.impl;

import java.util.List;
import nix.jdbcworkshop.entities.CarOrderStatus;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;

/**
 *
 * @author mednorcom
 */
public class CarOrderStatusDaoH2Test extends AbstractDaoH2Test {

    private CarOrderStatusDaoH2 carOrderStatusDaoInstance;

    public CarOrderStatusDaoH2Test() {
        super();
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSet(
                getClass().getClassLoader().getResourceAsStream(
                        dbunitConfig.getString("dbunit.car.order.status.initial")));
    }

    public void setUp() throws Exception {
        super.setUp();
        carOrderStatusDaoInstance = new CarOrderStatusDaoH2();
    }

    /**
     * Test of create method, of class CarOrderStatusDaoH2.
     */
    public void testCreate() throws Exception {
        CarOrderStatus carOrderStatus = new CarOrderStatus(null, "Assigned");
        carOrderStatusDaoInstance.create(carOrderStatus);
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("car_order_status");
        IDataSet expectedDataSet = new FlatXmlDataSet(getClass().getClassLoader()
                .getResourceAsStream(dbunitConfig
                        .getString("dbunit.car.order.status.expected.create")));
        ITable expectedTable = expectedDataSet.getTable("car_order_status");
        Assertion.assertEquals(expectedTable, DefaultColumnFilter
                .includedColumnsTable(actualTable, expectedTable.getTableMetaData().getColumns()));
        assertNotNull(carOrderStatus.getCarOrderStatusId());
    }

    /**
     * Test of update method, of class CarOrderStatusDaoH2.
     */
    public void testUpdate() throws Exception {
        CarOrderStatus carOrderStatus = new CarOrderStatus(null, "Assigned");
        carOrderStatusDaoInstance.create(carOrderStatus);
        carOrderStatus.setName("Re-Assigned");
        carOrderStatusDaoInstance.update(carOrderStatus);
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("car_order_status");
        IDataSet expectedDataSet = new FlatXmlDataSet(getClass().getClassLoader()
                .getResourceAsStream(dbunitConfig
                        .getString("dbunit.car.order.status.expected.update")));
        ITable expectedTable = expectedDataSet.getTable("car_order_status");
        Assertion.assertEquals(expectedTable, DefaultColumnFilter
                .includedColumnsTable(actualTable, expectedTable.getTableMetaData().getColumns()));
        //carOrderStatusDaoInstance.delete(carOrderStatus);
    }

    /**
     * Test of delete method, of class CarOrderStatusDaoH2.
     */
    public void testDelete() throws Exception {
        CarOrderStatus carOrderStatus = new CarOrderStatus(null, "Assigned");
        carOrderStatusDaoInstance.create(carOrderStatus);
        carOrderStatusDaoInstance.delete(carOrderStatus);
        carOrderStatusDaoInstance.delete(new CarOrderStatus((short) 3, null));
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("car_order_status");
        IDataSet expectedDataSet = new FlatXmlDataSet(getClass().getClassLoader()
                .getResourceAsStream(dbunitConfig
                        .getString("dbunit.car.order.status.expected.delete")));
        ITable expectedTable = expectedDataSet.getTable("car_order_status");
        Assertion.assertEquals(expectedTable, DefaultColumnFilter
                .includedColumnsTable(actualTable, expectedTable.getTableMetaData().getColumns()));
    }

    /**
     * Test of findCarById method, of class CarOrderStatusDaoH2.
     */
    public void testFindCarOrderStatusById() {
        CarOrderStatus carOrderStatus = carOrderStatusDaoInstance.findCarOrderStatusById(3);
        assertEquals(new Short((short) 3), carOrderStatus.getCarOrderStatusId());
        assertEquals("In Progress", carOrderStatus.getName());
    }

    public void testGetCarOrderStatusList() {
        List<CarOrderStatus> carOrderStatusList = carOrderStatusDaoInstance.getCarOrderStatusList();
        assertEquals(3, carOrderStatusList.size());
    }

    public void testGetCarOrderStatusListLimit() {
        List<CarOrderStatus> carOrderStatusListLimit =
                carOrderStatusDaoInstance.getCarOrderStatusList(2);
        assertEquals(2, carOrderStatusListLimit.size());
        assertEquals(new Short((short) 1), carOrderStatusListLimit.get(0).getCarOrderStatusId());
        assertEquals(new Short((short) 2), carOrderStatusListLimit.get(1).getCarOrderStatusId());
    }

    public void testGetCarOrderStatusListOffset() {
        List<CarOrderStatus> carOrderStatusListOffset = 
                carOrderStatusDaoInstance.getCarOrderStatusList(1, 2);
        assertEquals(2, carOrderStatusListOffset.size());
        assertEquals(new Short((short) 2), carOrderStatusListOffset.get(0).getCarOrderStatusId());
        assertEquals(new Short((short) 3), carOrderStatusListOffset.get(1).getCarOrderStatusId());
    }

}
