package com.nixsolutions.library.entity;

import com.nixsolutions.library.dao.ClientDAO;
import com.nixsolutions.library.entity.config.DBUnitConfig;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Assert;

import java.util.List;

/**
 * Created by Serko on 26.12.2015.
 */
public class TestClient extends DBUnitConfig {
    private Client client;

    public TestClient(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
        beforeData = new FlatXmlDataFileLoader().load("/Client/Init.xml");
        tester.setDataSet(beforeData);
        tester.onSetup();
    }

    public void testCreate() throws Exception {
        client = new Client("Alex", "Smirnov", "23-56-98", "mail@mail.com");
        ClientDAO clientDAO = daoFactory.getClientDAO();
        clientDAO.create(client);
        IDataSet expected = new FlatXmlDataFileLoader().load("/Client/Create.xml");
        IDataSet actual = tester.getConnection().createDataSet();
        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actual.getTable("client"),
                expected.getTableMetaData("client").getColumns());
        Assertion.assertEquals(expected.getTable("client"), filteredTable);
    }

    public void testUpdate() throws Exception {
        client = new Client(1, "Alex", "Smirnov", "23-56-98", "mail@mail.com");
        ClientDAO clientDAO = daoFactory.getClientDAO();
        clientDAO.update(client);
        IDataSet expected = new FlatXmlDataFileLoader().load("/Client/Update.xml");
        IDataSet actual = tester.getConnection().createDataSet();
        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actual.getTable("client"),
                expected.getTableMetaData("client").getColumns());
        Assertion.assertEquals(expected.getTable("client"), filteredTable);
    }

    public void testDelete() throws Exception {
        client = new Client(1, "Petr", "Ivanov", "111-45-78", "simple@mail.com");
        ClientDAO clientDAO = daoFactory.getClientDAO();
        clientDAO.delete(client);
        IDataSet expected = new FlatXmlDataFileLoader().load("/Client/Delete.xml");
        IDataSet actual = tester.getConnection().createDataSet();
        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actual.getTable("client"),
                expected.getTableMetaData("client").getColumns());
        Assertion.assertEquals(expected.getTable("client"), filteredTable);
    }

    public void testFindById() throws Exception {
        client = new Client(1, "Petr", "Ivanov", "111-45-78", "simple@mail.com");
        ClientDAO clientDAO = daoFactory.getClientDAO();
        Client actualClient;
        actualClient = clientDAO.findByID(client.getClientId());

        Assert.assertEquals(client.getClientId(), actualClient.getClientId());
        Assert.assertEquals(client.getFirstName(), actualClient.getFirstName());
        Assert.assertEquals(client.getLastName(), actualClient.getLastName());
        Assert.assertEquals(client.getPhone(), actualClient.getPhone());
        Assert.assertEquals(client.getEmail(), actualClient.getEmail());
    }

    public void testFindAll() throws Exception {
        ClientDAO clientDAO = daoFactory.getClientDAO();
        List<Client> clientList;
        clientList = clientDAO.findAll();

        Assert.assertEquals(2, clientList.size());

        Assert.assertEquals(new Integer(1), clientList.get(0).getClientId());
        Assert.assertEquals("Petr", clientList.get(0).getFirstName());
        Assert.assertEquals("Ivanov", clientList.get(0).getLastName());
        Assert.assertEquals("111-45-78", clientList.get(0).getPhone());
        Assert.assertEquals("simple@mail.com", clientList.get(0).getEmail());

        Assert.assertEquals(new Integer(2), clientList.get(1).getClientId());
        Assert.assertEquals("Ivan", clientList.get(1).getFirstName());
        Assert.assertEquals("Petrov", clientList.get(1).getLastName());
        Assert.assertEquals("175-54-67", clientList.get(1).getPhone());
        Assert.assertEquals("test@mail.com", clientList.get(1).getEmail());
    }
}
