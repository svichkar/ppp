package com.nixsolutions.servicestation.dao.impl;

import com.nixsolutions.servicestation.dao.CarOrderDAO;
import com.nixsolutions.servicestation.entity.CarOrder;
import com.nixsolutions.servicestation.util.TestJointUtil;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Assert;

import java.util.Date;
import java.util.List;
/**
 * Created by rybkinrolla on 05.01.2016.
 */
public class TestCarOrderDAO extends TestJointUtil {
    private CarOrder carOrder;

    public TestCarOrderDAO(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
        preCondition = new FlatXmlDataFileLoader().load("/CarOrder/initial.xml");
        tester.setDataSet(preCondition);
        tester.onSetup();
    }

    public void testCreate() throws Exception {
        carOrder = new CarOrder(10001, 2, new Date(1451900918000L));
        CarOrderDAO carOrderDAO = factoryDAO.getCarOrderDAO();
        carOrderDAO.create(carOrder);
        IDataSet expectedResult = new FlatXmlDataFileLoader().load("/CarOrder/create.xml");
        IDataSet actualResult = tester.getConnection().createDataSet();
        ITable table = DefaultColumnFilter.includedColumnsTable(actualResult.getTable("car_order"),
                expectedResult.getTableMetaData("car_order").getColumns());
        Assertion.assertEquals(expectedResult.getTable("car_order"), table);
    }

    public void testDelete() throws Exception {
        carOrder = new CarOrder(10002, 2, new Date(1451641718000L));
        carOrder.setCarOrderId(101);
        CarOrderDAO carOrderDAO = factoryDAO.getCarOrderDAO();
        carOrderDAO.delete(carOrder);
        IDataSet expectedResult = new FlatXmlDataFileLoader().load("/CarOrder/delete.xml");
        IDataSet actualResult = tester.getConnection().createDataSet();
        ITable table = DefaultColumnFilter.includedColumnsTable(actualResult.getTable("car_order"),
                expectedResult.getTableMetaData("car_order").getColumns());
        Assertion.assertEquals(expectedResult.getTable("car_order"), table);
    }

    public void testUpdate() throws Exception {
        carOrder = new CarOrder(10001, 1, new Date(1420451318000L));
        carOrder.setCarOrderId(100);
        CarOrderDAO carOrderDAO = factoryDAO.getCarOrderDAO();
        carOrderDAO.update(carOrder);
        IDataSet expectedResult = new FlatXmlDataFileLoader().load("/CarOrder/update.xml");
        IDataSet actualResult = tester.getConnection().createDataSet();
        ITable table = DefaultColumnFilter.includedColumnsTable(actualResult.getTable("car_order"),
                expectedResult.getTableMetaData("car_order").getColumns());
        Assertion.assertEquals(expectedResult.getTable("car_order"), table);
    }

    public void testFindById() throws Exception {
        CarOrderDAO carOrderDAO = factoryDAO.getCarOrderDAO();
        carOrder = carOrderDAO.findById(100);
        Assert.assertEquals(new Integer(10001), carOrder.getCarId());
        Assert.assertEquals(new Integer(1), carOrder.getCarOrderStatusId());
        Assert.assertEquals(java.sql.Date.valueOf("2015-12-31"), carOrder.getStartDate());
    }

    public void testFindAll() throws Exception {
        CarOrderDAO carOrderDAO = factoryDAO.getCarOrderDAO();
        List<CarOrder> carOrderList = carOrderDAO.findAll();
        Assert.assertEquals(2,carOrderList.size());
        Assert.assertEquals(new Integer(10001), carOrderList.get(0).getCarId());
        Assert.assertEquals(new Integer(1), carOrderList.get(0).getCarOrderStatusId());
        Assert.assertEquals(java.sql.Date.valueOf("2015-12-31"), carOrderList.get(0).getStartDate());

        Assert.assertEquals(new Integer(10002), carOrderList.get(1).getCarId());
        Assert.assertEquals(new Integer(2), carOrderList.get(1).getCarOrderStatusId());
        Assert.assertEquals(java.sql.Date.valueOf("2016-01-01"), carOrderList.get(1).getStartDate());
    }
}
