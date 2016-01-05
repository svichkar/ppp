package com.nixsolutions.servicestation.dao.impl;

import com.nixsolutions.servicestation.dao.CarTypeDAO;
import com.nixsolutions.servicestation.entity.CarType;
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
public class TestCarTypeDAO extends TestJointUtil {
    private CarType carType;

    public TestCarTypeDAO(String name) {
        super(name);
    }
    public void setUp() throws Exception {
        super.setUp();
        preCondition = new FlatXmlDataFileLoader().load("/CarType/initial.xml");
        tester.setDataSet(preCondition);
        tester.onSetup();
    }

    public void testCreate() throws Exception {
        carType = new CarType("UAZ","Patriot");
        CarTypeDAO carTypeDAO = factoryDAO.getCarTypeDAO();
        carTypeDAO.create(carType);
        IDataSet expectedResult = new FlatXmlDataFileLoader().load("/CarType/create.xml");
        IDataSet actualResult = tester.getConnection().createDataSet();
        ITable table = DefaultColumnFilter.includedColumnsTable(actualResult.getTable("car_type"),
                expectedResult.getTableMetaData("car_type").getColumns());
        Assertion.assertEquals(expectedResult.getTable("car_type"), table);
    }

    public void testDelete() throws Exception {
        carType = new CarType("VAZ","2101");
        carType.setCarTypeId(101);
        CarTypeDAO carTypeDAO = factoryDAO.getCarTypeDAO();
        carTypeDAO.delete(carType);
        IDataSet expectedResult = new FlatXmlDataFileLoader().load("/CarType/delete.xml");
        IDataSet actualResult = tester.getConnection().createDataSet();
        ITable table = DefaultColumnFilter.includedColumnsTable(actualResult.getTable("car_type"),
                expectedResult.getTableMetaData("car_type").getColumns());
        Assertion.assertEquals(expectedResult.getTable("car_type"), table);
    }

    public void testUpdate() throws Exception {
        carType = new CarType("Honda","Civic");
        carType.setCarTypeId(100);
        CarTypeDAO carTypeDAO = factoryDAO.getCarTypeDAO();
        carTypeDAO.update(carType);
        IDataSet expectedResult = new FlatXmlDataFileLoader().load("/CarType/update.xml");
        IDataSet actualResult = tester.getConnection().createDataSet();
        ITable table = DefaultColumnFilter.includedColumnsTable(actualResult.getTable("car_type"),
                expectedResult.getTableMetaData("car_type").getColumns());
        Assertion.assertEquals(expectedResult.getTable("car_type"), table);
    }

    public void testFindById() throws Exception {
        CarTypeDAO carTypeDAO = factoryDAO.getCarTypeDAO();
        carType = carTypeDAO.findById(100);
        Assert.assertEquals("Honda", carType.getBrand());
        Assert.assertEquals("Accord", carType.getModelName());
    }

    public void testFindAll() throws Exception {
        CarTypeDAO carTypeDAO = factoryDAO.getCarTypeDAO();
        List<CarType> carTypeList = carTypeDAO.findAll();
        Assert.assertEquals(2,carTypeList.size());
        Assert.assertEquals("Honda", carTypeList.get(0).getBrand());
        Assert.assertEquals("Accord", carTypeList.get(0).getModelName());
        Assert.assertEquals("VAZ", carTypeList.get(1).getBrand());
        Assert.assertEquals("2101", carTypeList.get(1).getModelName());
    }
}
