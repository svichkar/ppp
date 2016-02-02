package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.entity.Client;

public interface ClientService {
	public List<Client> getAllClients();
	public Client getClientById(Long clientId);
	public void createClient(Client client);
	public void updateClient(Client client);
	public void deleteClient(Client client);
	public Client getClientByName(String readerName);
	public List<Client> getClientsByName(String readerName);
}
