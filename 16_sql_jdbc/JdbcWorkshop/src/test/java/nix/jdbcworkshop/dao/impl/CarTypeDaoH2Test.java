/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.jdbcworkshop.dao.impl;

import java.util.List;
import nix.jdbcworkshop.entities.CarType;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;

/**
 *
 * @author mednorcom
 */
public class CarTypeDaoH2Test extends AbstractDaoH2Test {

    private CarTypeDaoH2 carTypeDaoInstance;

    public CarTypeDaoH2Test() {
        super();
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSet(
                getClass().getClassLoader().getResourceAsStream(dbunitConfig.getString("dbunit.car.type.initial")));
    }

    public void setUp() throws Exception {
        super.setUp();
        carTypeDaoInstance = new CarTypeDaoH2();
    }

    /**
     * Test of create method, of class CarTypeDaoH2.
     */
    public void testCreate() throws Exception {
        CarType carType = new CarType(null, "Tesla", "M");
        carTypeDaoInstance.create(carType);
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("car_type");
        IDataSet expectedDataSet = new FlatXmlDataSet(getClass().getClassLoader().getResourceAsStream(dbunitConfig.getString("dbunit.car.type.expected.create")));
        ITable expectedTable = expectedDataSet.getTable("car_type");
        Assertion.assertEquals(expectedTable, DefaultColumnFilter.includedColumnsTable(actualTable, expectedTable.getTableMetaData().getColumns()));
        assertNotNull(carType.getCarTypeId());
        //carTypeDaoInstance.delete(carType);
    }

    /**
     * Test of update method, of class CarTypeDaoH2.
     */
    public void testUpdate() throws Exception {
        CarType carType = new CarType(null, "Tesla", "M");
        carTypeDaoInstance.create(carType);
        carType.setModel("S");
        carTypeDaoInstance.update(carType);
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("car_type");
        IDataSet expectedDataSet = new FlatXmlDataSet(getClass().getClassLoader().getResourceAsStream(dbunitConfig.getString("dbunit.car.type.expected.update")));
        ITable expectedTable = expectedDataSet.getTable("car_type");
        Assertion.assertEquals(expectedTable, DefaultColumnFilter.includedColumnsTable(actualTable, expectedTable.getTableMetaData().getColumns()));
        //carTypeDaoInstance.delete(carType);
    }

    /**
     * Test of delete method, of class CarTypeDaoH2.
     */
    public void testDelete() throws Exception {
        CarType carType = new CarType(null, "Tesla", "M");
        carTypeDaoInstance.create(carType);
        carTypeDaoInstance.delete(carType);
        carTypeDaoInstance.delete(new CarType(new Long(5), null, null));
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("car_type");
        IDataSet expectedDataSet = new FlatXmlDataSet(getClass().getClassLoader().getResourceAsStream(dbunitConfig.getString("dbunit.car.type.expected.delete")));
        ITable expectedTable = expectedDataSet.getTable("car_type");
        Assertion.assertEquals(expectedTable, DefaultColumnFilter.includedColumnsTable(actualTable, expectedTable.getTableMetaData().getColumns()));
    }

    /**
     * Test of findCarById method, of class CarTypeDaoH2.
     */
    public void testFindCarById() {
        CarType carType = carTypeDaoInstance.findCarById(5);
        assertEquals(5, carType.getCarTypeId());
        assertEquals("Toyota", carType.getBrand());
        assertEquals("Corolla", carType.getModel());
    }

     public void testGetCarTypeList() {
        List<CarType> carTypeList = carTypeDaoInstance.getCarTypeList();
        assertEquals(5, carTypeList.size());       
    }
     
      public void testGetCarTypeListLimit() {
        List<CarType> carTypeListLimit = carTypeDaoInstance.getCarTypeList(4);    
        assertEquals(4, carTypeListLimit.size());
        assertEquals(1, carTypeListLimit.get(0).getCarTypeId());
        assertEquals(4, carTypeListLimit.get(3).getCarTypeId());
    }
    
    
    public void testGetCarTypeListOffset() {
        List<CarType> carTypeListOffset = carTypeDaoInstance.getCarTypeList(1,4);
        assertEquals(4, carTypeListOffset.size());
        assertEquals(2, carTypeListOffset.get(0).getCarTypeId());
        assertEquals(5, carTypeListOffset.get(3).getCarTypeId());
    }

}
