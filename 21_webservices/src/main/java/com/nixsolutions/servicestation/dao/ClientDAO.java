package com.nixsolutions.servicestation.dao;

import com.nixsolutions.servicestation.entity.Client;

import java.util.Set;

/**
 * Created by rybkinrolla on 29.12.2015.
 */
public interface ClientDAO extends GenericDAO<Client> {
    Set<Client> findClientsUsers();
}
