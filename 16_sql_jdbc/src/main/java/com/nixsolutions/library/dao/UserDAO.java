package com.nixsolutions.library.dao;

import com.nixsolutions.library.entity.User;

/**
 * Created by kozlovskij on 12/28/2015.
 */
public interface UserDAO extends GenericDAO<User> {
    User findByLogin(String login);
}
