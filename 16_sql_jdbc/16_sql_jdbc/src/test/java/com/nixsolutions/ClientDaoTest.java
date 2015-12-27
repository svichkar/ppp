package com.nixsolutions;

import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Assert;
import com.nixsolutions.dao.impl.ClientDaoImpl;
import com.nixsolutions.entity.Client;

public class ClientDaoTest extends DBUnitConfig {

	public ClientDaoTest(String name) {
		super(name);
	}

	public void setUp() throws Exception {
		super.setUp();
		beforeData = new FlatXmlDataFileLoader().load("/client/ClientInitialDataSet.xml");
		tester.setDataSet(beforeData);
		tester.onSetup();
	}
	
	public void testShouldretrieveClientById() throws Exception {
		Client client = new ClientDaoImpl().getClientById(1);
		Assert.assertEquals(new Integer(1), client.getClientId());
		Assert.assertEquals("Evgeniy", client.getFirstName());
		Assert.assertEquals("Fomin", client.getSecondName());
		Assert.assertEquals("123-41234-532", client.getPhone());
		Assert.assertEquals("evgeniy@email.com", client.getEmail());
	}
	
	public void testShouldReturnNullIfQueryReturnedNoResultsClientById() throws Exception {
		Client client = new ClientDaoImpl().getClientById(5);
		Assert.assertNull(client);
	}
	
	public void testShouldRetrieveAllClients() throws Exception {
		List<Client> clients = new ClientDaoImpl().getAllClients();
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/client/ClientInitialDataSet.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		Assertion.assertEquals(expectedData.getTable("client"), actualData.getTable("client"));
		Assert.assertEquals(expectedData.getTable("client").getRowCount(),clients.size());
	}

	public void testShouldDeleteClient() throws Exception {
		Client client = new ClientDaoImpl().getClientById(1);
		new ClientDaoImpl().deleteClient(client);
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/client/ClientDelete.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		Assertion.assertEquals(expectedData.getTable("client"), actualData.getTable("client"));
	}

	public void testShouldCreateClient() throws Exception {
		Client client = new Client();
		client.setFirstName("Vitaliy");
		client.setSecondName("Rybkin");
		client.setEmail("vitaliy@email.com");
		client.setPhone("123-23434-532");
		new ClientDaoImpl().createClient(client);
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/client/ClientCreate.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualData.getTable("client"), 
	            expectedData.getTable("client").getTableMetaData().getColumns());
	    Assertion.assertEquals(expectedData.getTable("client"), filteredTable); 
	}

	public void testShouldUpdateClient() throws Exception {
		Client client = new ClientDaoImpl().getClientById(3);
		client.setFirstName("Max");
		client.setSecondName("Kulishov");
		new ClientDaoImpl().updateClient(client);
		IDataSet expectedData = new FlatXmlDataFileLoader().load("/client/ClientUpdate.xml");
		IDataSet actualData = tester.getConnection().createDataSet();
		ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualData.getTable("client"), 
	            expectedData.getTable("client").getTableMetaData().getColumns());
	    Assertion.assertEquals(expectedData.getTable("client"), filteredTable);
	}
}
