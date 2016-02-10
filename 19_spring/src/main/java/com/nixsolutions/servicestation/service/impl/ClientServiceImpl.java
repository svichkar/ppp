package com.nixsolutions.servicestation.service.impl;

import com.nixsolutions.servicestation.dao.CarDAO;
import com.nixsolutions.servicestation.dao.ClientDAO;
import com.nixsolutions.servicestation.dao.RoleDAO;
import com.nixsolutions.servicestation.dao.UserDAO;
import com.nixsolutions.servicestation.entity.Client;
import com.nixsolutions.servicestation.entity.Role;
import com.nixsolutions.servicestation.entity.User;
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
    private ClientDAO clientDAO;
    @Autowired
    private RoleDAO roleDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private CarDAO carDAO;

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
    @Override
    public boolean createClientUser(Long roleId, String login, String password, String firstName, String lastName){
        Role role = roleDAO.findById(roleId);
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);
        Client client = new Client();
        client.setFirstName(firstName);
        client.setLastName(lastName);
        Set<User> userSet = userDAO.findAll();
        if (!userSet.contains(user)) {
            userDAO.create(user);
            client.setUser(user);
            clientDAO.create(client);
            return true;
        } else {
            return false;
        }

    }
    @Override
    public void updateClientUser(Long roleId, String login, String password, String firstName, String lastName,
                                 Long userId, Long clientId){
        Role role = roleDAO.findById(roleId);
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);
        Client client = new Client();
        client.setFirstName(firstName);
        client.setLastName(lastName);
        user.setUserId(userId);
        userDAO.update(user);
        client.setUser(user);
        client.setClientId(clientId);
        clientDAO.update(client);
    }

    @Override
    public boolean deleteClientUser(Long roleId, Long userId, Long clientId) {
        Role role = roleDAO.findById(roleId);
        if (!role.getRoleName().equals("manager")) {
            clientDAO.delete(clientDAO.findById(clientId));
            return true;
        } else {
            return false;
        }
    }
}
