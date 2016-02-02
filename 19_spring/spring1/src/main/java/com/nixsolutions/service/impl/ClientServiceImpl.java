package com.nixsolutions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.ClientDao;
import com.nixsolutions.entity.Client;
import com.nixsolutions.service.ClientService;

@Service
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
	public void createClient(Client client) {
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
