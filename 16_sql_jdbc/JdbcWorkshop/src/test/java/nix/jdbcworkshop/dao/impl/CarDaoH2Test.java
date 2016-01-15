/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.jdbcworkshop.dao.impl;

import java.util.List;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import nix.jdbcworkshop.entities.Car;
import nix.jdbcworkshop.entities.Car;
import org.dbunit.Assertion;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mednorcom
 */
public class CarDaoH2Test extends AbstractDaoH2Test {

    private CarDaoH2 carDaoInstance;

    public CarDaoH2Test() {
        super();
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        carDaoInstance = new CarDaoH2();
    }

    /**
     * Test of create method, of class CarDaoH2.
     */
    public void testCreate() throws Exception {
        Car car = new Car(null, "TEST-006", new Long(1), new Long(1));
        carDaoInstance.create(car);
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("car");
        IDataSet expectedDataSet = new FlatXmlDataSet(getClass().getClassLoader().getResourceAsStream(dbunitConfig.getString("dbunit.car.expected.create")));
        ITable expectedTable = expectedDataSet.getTable("car");
        Assertion.assertEquals(expectedTable, DefaultColumnFilter.includedColumnsTable(actualTable, expectedTable.getTableMetaData().getColumns()));
        assertNotNull(car.getCarId());
    }

    /**
     * Test of update method, of class CarDaoH2.
     */
    public void testUpdate() throws Exception {
        Car car = new Car(null, "TEST-006", new Long(1), new Long(1));
        carDaoInstance.create(car);
        car.setClientId(new Long(2));
        carDaoInstance.update(car);
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("car");
        IDataSet expectedDataSet = new FlatXmlDataSet(getClass().getClassLoader().getResourceAsStream(dbunitConfig.getString("dbunit.car.expected.update")));
        ITable expectedTable = expectedDataSet.getTable("car");
        Assertion.assertEquals(expectedTable, DefaultColumnFilter.includedColumnsTable(actualTable, expectedTable.getTableMetaData().getColumns()));
    }

    /**
     * Test of delete method, of class CarDaoH2.
     */
    public void testDelete() throws Exception {
        Car car = new Car(null, "TEST-006", new Long(1), new Long(1));
        carDaoInstance.create(car);
        carDaoInstance.delete(car);
        carDaoInstance.delete(new Car(new Long(5), null, null, null));
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("car");
        IDataSet expectedDataSet = new FlatXmlDataSet(getClass().getClassLoader().getResourceAsStream(dbunitConfig.getString("dbunit.car.expected.delete")));
        ITable expectedTable = expectedDataSet.getTable("car");
        Assertion.assertEquals(expectedTable, DefaultColumnFilter.includedColumnsTable(actualTable, expectedTable.getTableMetaData().getColumns()));
    }

    /**
     * Test of findCarById method, of class CarDaoH2.
     */
    public void testFindCarById() {
        Car car = carDaoInstance.findCarById(5);
        assertEquals(5, car.getCarId());
        assertEquals("TEST-005", car.getSerialId());
        assertEquals(5, car.getCarTypeId());
    }

    public void testGetCarList() {
        List<Car> carList = carDaoInstance.getCarList();
        assertEquals(5, carList.size());
    }

    public void testGetCarListLimit() {
        List<Car> carListLimit = carDaoInstance.getCarList(4);
        assertEquals(4, carListLimit.size());
        assertEquals(1, carListLimit.get(0).getCarId());
        assertEquals(4, carListLimit.get(3).getCarId());
    }

    public void testGetCarListOffset() {
        List<Car> carListOffset = carDaoInstance.getCarList(1, 4);
        assertEquals(4, carListOffset.size());
        assertEquals(2, carListOffset.get(0).getCarId());
        assertEquals(5, carListOffset.get(3).getCarId());
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new CompositeDataSet(new IDataSet[]{
            new FlatXmlDataSet(getClass().getClassLoader()
            .getResourceAsStream(dbunitConfig.getString("dbunit.car.type.initial"))),
            new FlatXmlDataSet(getClass().getClassLoader()
            .getResourceAsStream(dbunitConfig.getString("dbunit.client.initial"))),
            new FlatXmlDataSet(getClass().getClassLoader()
            .getResourceAsStream(dbunitConfig.getString("dbunit.car.initial")))
        });
    }

}
