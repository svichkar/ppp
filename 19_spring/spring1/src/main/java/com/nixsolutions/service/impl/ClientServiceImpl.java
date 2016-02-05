package com.nixsolutions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.dao.ClientDao;
import com.nixsolutions.entity.Client;
import com.nixsolutions.service.ClientService;

@Service("clientService")
@Transactional
public class ClientServiceImpl implements ClientService{

	@Autowired
	private ClientDao clientDao;
	
	@Override
	public List<Client> getAllClients() {
		return clientDao.getAllClients();
	}

	@Override
	public Client getClientById(Long clientId) {
		return clientDao.getClientById(clientId);
	}

	@Override
	public void createClient(String firstName, String lastName, String email) {
		Client client = new Client();
		client.setFirstName(firstName);
		client.setSecondName(lastName);
		client.setEmail(email);
		clientDao.createClient(client);
	}

	@Override
	public void updateClient(Client client) {
		clientDao.updateClient(client);
	}

	@Override
	public void deleteClient(Client client) {
		clientDao.deleteClient(client);
	}

	@Override
	public Client getClientByName(String readerName) {
		return clientDao.getClientByName(readerName);
	}

	@Override
	public List<Client> getClientsByName(String readerName) {
		return clientDao.getClientsByName(readerName);
	}

}
