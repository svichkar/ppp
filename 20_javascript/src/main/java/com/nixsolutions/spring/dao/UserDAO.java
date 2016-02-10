package com.nixsolutions.spring.dao;

import com.nixsolutions.spring.entity.User;

/**
 * Created by kozlovskij on 12/28/2015.
 */
public interface UserDAO extends GenericDAO<User> {
    User findByLogin(String login);
}
