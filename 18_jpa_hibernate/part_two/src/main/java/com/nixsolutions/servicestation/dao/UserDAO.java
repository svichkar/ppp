package com.nixsolutions.servicestation.dao;

import com.nixsolutions.servicestation.entity.User;

import java.util.Set;

/**
 * Created by rybkinrolla on 06.01.2016.
 */
public interface UserDAO extends GenericDAO<User> {

    User findByLogin(String login);
    Set<User> findClientsUsers();
}
