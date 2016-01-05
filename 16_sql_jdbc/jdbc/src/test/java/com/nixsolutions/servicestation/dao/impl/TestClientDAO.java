package com.nixsolutions.servicestation.dao.impl;

import com.nixsolutions.servicestation.dao.ClientDAO;
import com.nixsolutions.servicestation.entity.Client;
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
public class TestClientDAO extends TestJointUtil {
    private Client client;

    public TestClientDAO(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
        preCondition = new FlatXmlDataFileLoader().load("/Client/initial.xml");
        tester.setDataSet(preCondition);
        tester.onSetup();
    }

    public void testCreate() throws Exception {
        client = new Client("Ned","Flanders");
        ClientDAO clientDAO = factoryDAO.getClientDAO();
        clientDAO.create(client);
        IDataSet expectedResult = new FlatXmlDataFileLoader().load("/Client/create.xml");
        IDataSet actualResult = tester.getConnection().createDataSet();
        ITable table = DefaultColumnFilter.includedColumnsTable(actualResult.getTable("client"),
                expectedResult.getTableMetaData("client").getColumns());
        Assertion.assertEquals(expectedResult.getTable("client"), table);
    }

    public void testDelete() throws Exception {
        client = new Client ("Bart","Simpson");
        client.setClientId(101);
        ClientDAO clientDAO = factoryDAO.getClientDAO();
        clientDAO.delete(client);
        IDataSet expectedResult = new FlatXmlDataFileLoader().load("/Client/delete.xml");
        IDataSet actualResult = tester.getConnection().createDataSet();
        ITable table = DefaultColumnFilter.includedColumnsTable(actualResult.getTable("client"),
                expectedResult.getTableMetaData("client").getColumns());
        Assertion.assertEquals(expectedResult.getTable("client"), table);
    }

    public void testUpdate() throws Exception {
        client = new Client ("Homer","Idiot Simpson");
        client.setClientId(100);
        ClientDAO clientDAO = factoryDAO.getClientDAO();
        clientDAO.update(client);
        IDataSet expectedResult = new FlatXmlDataFileLoader().load("/Client/update.xml");
        IDataSet actualResult = tester.getConnection().createDataSet();
        ITable table = DefaultColumnFilter.includedColumnsTable(actualResult.getTable("client"),
                expectedResult.getTableMetaData("client").getColumns());
        Assertion.assertEquals(expectedResult.getTable("client"), table);
    }

    public void testFindById() throws Exception {
        ClientDAO clientDAO = factoryDAO.getClientDAO();
        client = clientDAO.findById(100);
        Assert.assertEquals("Homer", client.getFirstName());
        Assert.assertEquals("Simpson", client.getLastName());
    }

    public void testFindAll() throws Exception {
        ClientDAO clientDAO = factoryDAO.getClientDAO();
        List<Client> clientList = clientDAO.findAll();
        Assert.assertEquals(2,clientList.size());
        Assert.assertEquals("Homer", clientList.get(0).getFirstName());
        Assert.assertEquals("Simpson", clientList.get(0).getLastName());
        Assert.assertEquals("Bart", clientList.get(1).getFirstName());
        Assert.assertEquals("Simpson", clientList.get(1).getLastName());
    }
}
