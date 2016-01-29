package com.nixsolutions.servicestation.service.impl;

import com.nixsolutions.servicestation.dao.ClientDAO;
import com.nixsolutions.servicestation.entity.Client;
import com.nixsolutions.servicestation.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by rybkinrolla on 04.01.2016.
 */
@Service("clientService")
@Transactional
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientDAO clientDAO;

    public Set<Client> findClientsUsers() {
        Set<Client> clientSet = clientDAO.findClientsUsers();
        return clientSet;
    }

    @Override
    public void create(Client entity) {
        clientDAO.create(entity);
    }

    @Override
    public void update(Client entity) {
        clientDAO.update(entity);
    }

    @Override
    public void delete(Client entity) {
        clientDAO.delete(entity);
    }

    @Override
    public Client findById(Long id) {
        Client client = clientDAO.findById(id);
        return client;
    }

    @Override
    public Set<Client> findAll() {
        Set<Client> clientSet = clientDAO.findAll();
        return clientSet;
    }
}
