/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.jdbcworkshop.dao.impl;

import java.util.List;
import nix.jdbcworkshop.entities.Client;
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
public class ClientDaoH2Test extends AbstractDaoH2Test {

    private ClientDaoH2 clientDaoInstance;

    public ClientDaoH2Test() {
        super();
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new CompositeDataSet(new IDataSet[]{
            new FlatXmlDataSet(getClass().getClassLoader()
            .getResourceAsStream(dbunitConfig.getString("dbunit.web.role.initial"))),
            new FlatXmlDataSet(getClass().getClassLoader()
            .getResourceAsStream(dbunitConfig.getString("dbunit.web.user.initial"))),
            new FlatXmlDataSet(getClass().getClassLoader()
            .getResourceAsStream(dbunitConfig.getString("dbunit.client.initial")))
        });
    }

    public void setUp() throws Exception {
        super.setUp();
        clientDaoInstance = new ClientDaoH2();
    }

    /**
     * Test of create method, of class ClientDaoH2.
     */
    public void testCreate() throws Exception {
        Client client = new Client(null, "Name6", "Sname6", null);
        clientDaoInstance.create(client);
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("client");
        IDataSet expectedDataSet = new FlatXmlDataSet(getClass().getClassLoader()
                .getResourceAsStream(dbunitConfig.getString("dbunit.client.expected.create")));
        ITable expectedTable = expectedDataSet.getTable("client");
        Assertion.assertEquals(expectedTable, DefaultColumnFilter.includedColumnsTable(
                actualTable, expectedTable.getTableMetaData().getColumns()));
        assertNotNull(client.getClientId());
    }

    /**
     * Test of update method, of class ClientDaoH2.
     */
    public void testUpdate() throws Exception {
        Client client = new Client(null, "Name6", "Sname6", null);
        clientDaoInstance.create(client);
        client.setLastName("Sname7");
        clientDaoInstance.update(client);
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("client");
        IDataSet expectedDataSet = new FlatXmlDataSet(getClass().getClassLoader()
                .getResourceAsStream(dbunitConfig.getString("dbunit.client.expected.update")));
        ITable expectedTable = expectedDataSet.getTable("client");
        Assertion.assertEquals(expectedTable, DefaultColumnFilter.includedColumnsTable(
                actualTable, expectedTable.getTableMetaData().getColumns()));
    }

    /**
     * Test of delete method, of class ClientDaoH2.
     */
    public void testDelete() throws Exception {
        Client client = new Client(null, "Name6", "Sname6", null);
        clientDaoInstance.create(client);
        clientDaoInstance.delete(client);
        clientDaoInstance.delete(new Client((long) 5, null, null, null));
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("client");
        IDataSet expectedDataSet = new FlatXmlDataSet(getClass().getClassLoader()
                .getResourceAsStream(dbunitConfig.getString("dbunit.client.expected.delete")));
        ITable expectedTable = expectedDataSet.getTable("client");
        Assertion.assertEquals(expectedTable, DefaultColumnFilter.includedColumnsTable(
                actualTable, expectedTable.getTableMetaData().getColumns()));
    }

    /**
     * Test of findCarById method, of class ClientDaoH2.
     */
    public void testFindClientById() {
        Client client = clientDaoInstance.findClientById(5);
        assertEquals(new Long(5), client.getClientId());
        assertEquals("Name5", client.getFirstName());
        assertEquals("Sname5", client.getLastName());
    }

    public void testGetClientList() {
        List<Client> clientList = clientDaoInstance.getClientList();
        assertEquals(5, clientList.size());
    }

    public void testGetClientListLimit() {
        List<Client> clientListLimit = clientDaoInstance.getClientList(4);
        assertEquals(4, clientListLimit.size());
        assertEquals(new Long(1), clientListLimit.get(0).getClientId());
        assertEquals(new Long(4), clientListLimit.get(3).getClientId());
    }

    public void testGetClientListOffset() {
        List<Client> clientListOffset = clientDaoInstance.getClientList(1, 4);
        assertEquals(4, clientListOffset.size());
        assertEquals(new Long(2), clientListOffset.get(0).getClientId());
        assertEquals(new Long(5), clientListOffset.get(3).getClientId());
    }

}
