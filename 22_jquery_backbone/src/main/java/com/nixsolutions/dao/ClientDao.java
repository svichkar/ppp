package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.Client;

public interface ClientDao {
	 List<Client> getAllClients();
	 Client getClientById(Long clientId);
	 void createClient(Client client);
	 void updateClient(Client client);
	 void deleteClient(Client client);
	 Client getClientByName(String readerName);
	 List<Client> getClientsByName(String readerName);
}
