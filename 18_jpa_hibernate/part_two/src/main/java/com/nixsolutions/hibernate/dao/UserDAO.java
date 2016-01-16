package com.nixsolutions.hibernate.dao;

import com.nixsolutions.hibernate.entity.User;

/**
 * Created by kozlovskij on 12/28/2015.
 */
public interface UserDAO extends GenericDAO<User> {
    User findByLogin (String login);
}
