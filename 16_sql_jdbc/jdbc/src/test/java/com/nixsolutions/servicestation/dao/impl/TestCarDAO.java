package com.nixsolutions.servicestation.dao.impl;

import com.nixsolutions.servicestation.dao.CarDAO;
import com.nixsolutions.servicestation.util.TestJointUtil;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Assert;
import com.nixsolutions.servicestation.entity.Car;

import java.util.List;

/**
 * Created by rybkinrolla on 04.01.2016.
 */
public class TestCarDAO extends TestJointUtil {
    private Car car;

    public TestCarDAO(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
        preCondition = new FlatXmlDataFileLoader().load("/Car/initial.xml");
        tester.setDataSet(preCondition);
        tester.onSetup();
    }

    public void testCreate() throws Exception {
        car = new Car(10003, "yyzz", 2, 1);
        CarDAO carDAO = factoryDAO.getCarDAO();
        carDAO.create(car);
        IDataSet expectedResult = new FlatXmlDataFileLoader().load("/Car/create.xml");
        IDataSet actualResult = tester.getConnection().createDataSet();
        ITable table = DefaultColumnFilter.includedColumnsTable(actualResult.getTable("car"),
                expectedResult.getTableMetaData("car").getColumns());
        Assertion.assertEquals(expectedResult.getTable("car"), table);
    }

    public void testDelete() throws Exception {
        car = new Car(10002, "xxzz", 2, 2);
        CarDAO carDAO = factoryDAO.getCarDAO();
        carDAO.delete(car);
        IDataSet expectedResult = new FlatXmlDataFileLoader().load("/Car/delete.xml");
        IDataSet actualResult = tester.getConnection().createDataSet();
        ITable table = DefaultColumnFilter.includedColumnsTable(actualResult.getTable("car"),
                expectedResult.getTableMetaData("car").getColumns());
        Assertion.assertEquals(expectedResult.getTable("car"), table);
    }

    public void testUpdate() throws Exception {
        car = new Car(10001, "aabbffff", 1, 1);
        CarDAO carDAO = factoryDAO.getCarDAO();
        carDAO.update(car);
        IDataSet expectedResult = new FlatXmlDataFileLoader().load("/Car/update.xml");
        IDataSet actualResult = tester.getConnection().createDataSet();
        ITable table = DefaultColumnFilter.includedColumnsTable(actualResult.getTable("car"),
                expectedResult.getTableMetaData("car").getColumns());
        Assertion.assertEquals(expectedResult.getTable("car"), table);
    }

    public void testFindById() throws Exception {
        CarDAO carDAO = factoryDAO.getCarDAO();
        car = carDAO.findById(10001);
        Assert.assertEquals(new Integer(10001), car.getCarId());
        Assert.assertEquals("aabb", car.getSerialId());
        Assert.assertEquals(new Integer(1), car.getCarTypeId());
        Assert.assertEquals(new Integer(1), car.getClientId());
    }

    public void testFindAll() throws Exception {
        CarDAO carDAO = factoryDAO.getCarDAO();
        List<Car> carList = carDAO.findAll();
        Assert.assertEquals(2,carList.size());
        Assert.assertEquals(new Integer(10001), carList.get(0).getCarId());
        Assert.assertEquals("aabb", carList.get(0).getSerialId());
        Assert.assertEquals(new Integer(1), carList.get(0).getCarTypeId());
        Assert.assertEquals(new Integer(1), carList.get(0).getClientId());
        Assert.assertEquals(new Integer(10002), carList.get(1).getCarId());
        Assert.assertEquals("xxzz", carList.get(1).getSerialId());
        Assert.assertEquals(new Integer(2), carList.get(1).getCarTypeId());
        Assert.assertEquals(new Integer(2), carList.get(1).getClientId());
    }

}
