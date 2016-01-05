package com.nixsolutions.servicestation.dao.impl;

import com.nixsolutions.servicestation.dao.EmployeeCarOrderDAO;
import com.nixsolutions.servicestation.entity.EmployeeCarOrder;
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
public class TestEmployeeCarOrderDAO extends TestJointUtil {
    private EmployeeCarOrder employeeCarOrder;

    public TestEmployeeCarOrderDAO(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
        preCondition = new FlatXmlDataFileLoader().load("/EmployeeCarOrder/initial.xml");
        tester.setDataSet(preCondition);
        tester.onSetup();
    }

    public void testCreate() throws Exception {
        employeeCarOrder = new EmployeeCarOrder(502, 10, 2);
        EmployeeCarOrderDAO employeeCarOrderDAO = factoryDAO.getEmployeeCarOrderDAO();
        employeeCarOrderDAO.create(employeeCarOrder);
        IDataSet expectedResult = new FlatXmlDataFileLoader().load("/EmployeeCarOrder/create.xml");
        IDataSet actualResult = tester.getConnection().createDataSet();
        ITable table = DefaultColumnFilter.includedColumnsTable(actualResult.getTable("employee_car_order"),
                expectedResult.getTableMetaData("employee_car_order").getColumns());
        Assertion.assertEquals(expectedResult.getTable("employee_car_order"), table);
    }

    public void testDelete() throws Exception {
        employeeCarOrder = new EmployeeCarOrder(501, 11, 2);
        EmployeeCarOrderDAO employeeCarOrderDAO = factoryDAO.getEmployeeCarOrderDAO();
        employeeCarOrderDAO.delete(employeeCarOrder);
        IDataSet expectedResult = new FlatXmlDataFileLoader().load("/EmployeeCarOrder/delete.xml");
        IDataSet actualResult = tester.getConnection().createDataSet();
        ITable table = DefaultColumnFilter.includedColumnsTable(actualResult.getTable("employee_car_order"),
                expectedResult.getTableMetaData("employee_car_order").getColumns());
        Assertion.assertEquals(expectedResult.getTable("employee_car_order"), table);
    }

    public void testUpdate() throws Exception {
        employeeCarOrder = new EmployeeCarOrder(500, 10, 2);
        EmployeeCarOrderDAO employeeCarOrderDAO = factoryDAO.getEmployeeCarOrderDAO();
        employeeCarOrderDAO.update(employeeCarOrder);
        IDataSet expectedResult = new FlatXmlDataFileLoader().load("/EmployeeCarOrder/update.xml");
        IDataSet actualResult = tester.getConnection().createDataSet();
        ITable table = DefaultColumnFilter.includedColumnsTable(actualResult.getTable("employee_car_order"),
                expectedResult.getTableMetaData("employee_car_order").getColumns());
        Assertion.assertEquals(expectedResult.getTable("employee_car_order"), table);
    }

    public void testFindById() throws Exception {
        EmployeeCarOrderDAO employeeCarOrderDAO = factoryDAO.getEmployeeCarOrderDAO();
        employeeCarOrder = employeeCarOrderDAO.findById(500);
        Assert.assertEquals(new Integer(1), employeeCarOrder.getCarOrderId());
    }

    public void testFindAll() throws Exception {
        EmployeeCarOrderDAO employeeCarOrderDAO = factoryDAO.getEmployeeCarOrderDAO();
        List<EmployeeCarOrder> employeeCarOrderList = employeeCarOrderDAO.findAll();
        Assert.assertEquals(2,employeeCarOrderList.size());
        Assert.assertEquals(new Integer(10), employeeCarOrderList.get(0).getEmployeeId());
        Assert.assertEquals(new Integer(1), employeeCarOrderList.get(0).getCarOrderId());
        Assert.assertEquals(new Integer(11), employeeCarOrderList.get(1).getEmployeeId());
        Assert.assertEquals(new Integer(2), employeeCarOrderList.get(1).getCarOrderId());
    }
}
