package com.nixsolutions.servicestation.dao.impl;

import com.nixsolutions.servicestation.dao.CarOrderStatusDAO;
import com.nixsolutions.servicestation.entity.CarOrderStatus;
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
public class TestCarOrderStatusDAO extends TestJointUtil {
    private CarOrderStatus carOrderStatus;

    public TestCarOrderStatusDAO(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
        preCondition = new FlatXmlDataFileLoader().load("/CarOrderStatus/initial.xml");
        tester.setDataSet(preCondition);
        tester.onSetup();
    }

    public void testCreate() throws Exception {
        carOrderStatus = new CarOrderStatus("Pending");
        CarOrderStatusDAO carOrderStatusDAO = factoryDAO.getCarOrderStatusDAO();
        carOrderStatusDAO.create(carOrderStatus);
        IDataSet expectedResult = new FlatXmlDataFileLoader().load("/CarOrderStatus/create.xml");
        IDataSet actualResult = tester.getConnection().createDataSet();
        ITable table = DefaultColumnFilter.includedColumnsTable(actualResult.getTable("car_order_status"),
                expectedResult.getTableMetaData("car_order_status").getColumns());
        Assertion.assertEquals(expectedResult.getTable("car_order_status"), table);
    }

    public void testDelete() throws Exception {
        carOrderStatus = new CarOrderStatus("In progress");
        carOrderStatus.setCarOrderStatusId(101);
        CarOrderStatusDAO carOrderStatusDAO = factoryDAO.getCarOrderStatusDAO();
        carOrderStatusDAO.delete(carOrderStatus);
        IDataSet expectedResult = new FlatXmlDataFileLoader().load("/CarOrderStatus/delete.xml");
        IDataSet actualResult = tester.getConnection().createDataSet();
        ITable table = DefaultColumnFilter.includedColumnsTable(actualResult.getTable("car_order_status"),
                expectedResult.getTableMetaData("car_order_status").getColumns());
        Assertion.assertEquals(expectedResult.getTable("car_order_status"), table);
    }

    public void testUpdate() throws Exception {
        carOrderStatus = new CarOrderStatus("Done");
        carOrderStatus.setCarOrderStatusId(100);
        CarOrderStatusDAO carOrderStatusDAO = factoryDAO.getCarOrderStatusDAO();
        carOrderStatusDAO.update(carOrderStatus);
        IDataSet expectedResult = new FlatXmlDataFileLoader().load("/CarOrderStatus/update.xml");
        IDataSet actualResult = tester.getConnection().createDataSet();
        ITable table = DefaultColumnFilter.includedColumnsTable(actualResult.getTable("car_order_status"),
                expectedResult.getTableMetaData("car_order_status").getColumns());
        Assertion.assertEquals(expectedResult.getTable("car_order_status"), table);
    }

    public void testFindById() throws Exception {
        CarOrderStatusDAO carOrderStatusDAO = factoryDAO.getCarOrderStatusDAO();
        carOrderStatus = carOrderStatusDAO.findById(100);
        Assert.assertEquals("Complete", carOrderStatus.getName());
    }

    public void testFindAll() throws Exception {
        CarOrderStatusDAO carOrderStatusDAO = factoryDAO.getCarOrderStatusDAO();
        List<CarOrderStatus> carOrderStatusList = carOrderStatusDAO.findAll();
        Assert.assertEquals(2,carOrderStatusList.size());
        Assert.assertEquals("Complete", carOrderStatusList.get(0).getName());
        Assert.assertEquals("In Progress", carOrderStatusList.get(1).getName());
    }
}
