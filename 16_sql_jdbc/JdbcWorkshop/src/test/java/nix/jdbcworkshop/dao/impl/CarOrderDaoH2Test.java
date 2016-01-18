/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.jdbcworkshop.dao.impl;

import java.util.Calendar;
import java.util.List;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import nix.jdbcworkshop.entities.CarOrder;
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
public class CarOrderDaoH2Test extends AbstractDaoH2Test {

    private CarOrderDaoH2 carOrderDaoInstance;

    public CarOrderDaoH2Test() {
        super();
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        carOrderDaoInstance = new CarOrderDaoH2();
    }

    /**
     * Test of create method, of class CarOrderDaoH2.
     */
    public void testCreate() throws Exception {
        Calendar startDate = Calendar.getInstance();
        startDate.set(2016, 00, 06, 10, 00, 00);
        startDate.set(Calendar.MILLISECOND, 0);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2016, 00, 06, 19, 00, 00);
        endDate.set(Calendar.MILLISECOND, 0);
        CarOrder carOrder = new CarOrder(null, new Long(1), new Short((short) 3),
                startDate.getTime(), endDate.getTime());
        carOrderDaoInstance.create(carOrder);
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("car_order");
        IDataSet expectedDataSet = new FlatXmlDataSet(getClass().getClassLoader()
                .getResourceAsStream(dbunitConfig.getString("dbunit.car.order.expected.create")));
        ITable expectedTable = expectedDataSet.getTable("car_order");
        Assertion.assertEquals(expectedTable, DefaultColumnFilter
                .includedColumnsTable(actualTable, expectedTable.getTableMetaData().getColumns()));
        assertNotNull(carOrder.getCarOrderId());
    }

    /**
     * Test of update method, of class CarOrderDaoH2.
     */
    public void testUpdate() throws Exception {
        Calendar startDate = Calendar.getInstance();
        startDate.set(2016, 00, 06, 10, 00, 00);
        startDate.set(Calendar.MILLISECOND, 0);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2016, 00, 06, 19, 00, 00);
        endDate.set(Calendar.MILLISECOND, 0);
        CarOrder carOrder = new CarOrder(null, new Long(1), new Short((short) 3), 
                startDate.getTime(), endDate.getTime());
        carOrderDaoInstance.create(carOrder);
        carOrder.setCarOrderStatusId((short) (1));
        carOrderDaoInstance.update(carOrder);
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("car_order");
        IDataSet expectedDataSet = new FlatXmlDataSet(getClass().getClassLoader()
                .getResourceAsStream(dbunitConfig.getString("dbunit.car.order.expected.update")));
        ITable expectedTable = expectedDataSet.getTable("car_order");
        Assertion.assertEquals(expectedTable, DefaultColumnFilter
                .includedColumnsTable(actualTable, expectedTable.getTableMetaData().getColumns()));
    }

    /**
     * Test of delete method, of class CarOrderDaoH2.
     */
    public void testDelete() throws Exception {
        Calendar startDate = Calendar.getInstance();
        startDate.set(2016, 00, 06, 10, 00, 00);
        startDate.set(Calendar.MILLISECOND, 0);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2016, 00, 06, 19, 00, 00);
        endDate.set(Calendar.MILLISECOND, 0);
        CarOrder carOrder = new CarOrder(null, new Long(1), new Short((short) 3), 
                startDate.getTime(), endDate.getTime());
        carOrderDaoInstance.create(carOrder);
        carOrderDaoInstance.delete(carOrder);
        carOrderDaoInstance.delete(new CarOrder(new Long(5), null, null, null, null));
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("car_order");
        IDataSet expectedDataSet = new FlatXmlDataSet(getClass().getClassLoader()
                .getResourceAsStream(dbunitConfig.getString("dbunit.car.order.expected.delete")));
        ITable expectedTable = expectedDataSet.getTable("car_order");
        Assertion.assertEquals(expectedTable, DefaultColumnFilter
                .includedColumnsTable(actualTable, expectedTable.getTableMetaData().getColumns()));
    }

    /**
     * Test of findCarOrderById method, of class CarOrderDaoH2.
     */
    public void testFindCarOrderById() {
        Calendar startDate = Calendar.getInstance();
        startDate.set(2016, 00, 05, 10, 00, 00);
        startDate.set(Calendar.MILLISECOND, 0);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2016, 00, 05, 19, 00, 00);
        endDate.set(Calendar.MILLISECOND, 0);
        CarOrder carOrder = carOrderDaoInstance.findCarOrderById(5);
        assertEquals(new Long(5), carOrder.getCarOrderId());
        assertEquals(new Long(5), carOrder.getCarId());
        assertEquals(new Short((short) 2), carOrder.getCarOrderStatusId());
        assertEquals(startDate.getTime(), carOrder.getStartDate());
        assertEquals(endDate.getTime(), carOrder.getEndDate());
    }

    public void testGetCarOrderList() {
        List<CarOrder> carOrderList = carOrderDaoInstance.getCarOrderList();
        assertEquals(5, carOrderList.size());
    }

    public void testGetCarOrderListLimit() {
        List<CarOrder> carOrderListLimit = carOrderDaoInstance.getCarOrderList(4);
        assertEquals(4, carOrderListLimit.size());
        assertEquals(new Long(1), carOrderListLimit.get(0).getCarOrderId());
        assertEquals(new Long(4), carOrderListLimit.get(3).getCarOrderId());
    }

    public void testGetCarOrderListOffset() {
        List<CarOrder> carOrderListOffset = carOrderDaoInstance.getCarOrderList(1, 4);
        assertEquals(4, carOrderListOffset.size());
        assertEquals(new Long(2), carOrderListOffset.get(0).getCarOrderId());
        assertEquals(new Long(5), carOrderListOffset.get(3).getCarOrderId());
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new CompositeDataSet(new IDataSet[]{
            new FlatXmlDataSet(getClass().getClassLoader()
            .getResourceAsStream(dbunitConfig.getString("dbunit.car.type.initial"))),
            new FlatXmlDataSet(getClass().getClassLoader()
            .getResourceAsStream(dbunitConfig.getString("dbunit.client.initial"))),
            new FlatXmlDataSet(getClass().getClassLoader()
            .getResourceAsStream(dbunitConfig.getString("dbunit.car.initial"))),
            new FlatXmlDataSet(getClass().getClassLoader()
            .getResourceAsStream(dbunitConfig.getString("dbunit.car.order.status.initial"))),
            new FlatXmlDataSet(getClass().getClassLoader()
            .getResourceAsStream(dbunitConfig.getString("dbunit.car.order.initial")))
        });
    }

}
