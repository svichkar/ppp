package com.nixsolutions.servicestation.service;

import com.nixsolutions.servicestation.entity.Client;

import java.util.Set;

/**
 * Created by rybkinrolla on 29.12.2015.
 */
public interface ClientService extends GenericService<Client> {
    Set<Client> findClientsUsers();
    boolean createClientUser(Long roleId, String login, String password, String firstName, String lastName);
}
