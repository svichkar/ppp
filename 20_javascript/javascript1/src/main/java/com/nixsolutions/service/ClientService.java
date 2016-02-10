package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.entity.Client;

public interface ClientService {
	 List<Client> getAllClients();
	 Client getClientById(Long clientId);
	 void createClient(String firstName, String lastName, String email);
	 void updateClient(Client client);
	 void deleteClient(Client client);
	 Client getClientByName(String readerName);
	 List<Client> getClientsByName(String readerName);
}
