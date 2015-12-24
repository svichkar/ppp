package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.UserDao;
import com.nixsolutions.studentgrade.entity.User;

import java.util.List;

/**
 * Created by svichkar on 12/23/2015.
 */
public class UserDaoImpl implements UserDao {
    public boolean create(User user) {
        return false;
    }

    public int update(User user, User newUser) {
        return 0;
    }

    public int delete(User use) {
        return 0;
    }

    public List<User> findAll() {
        return null;
    }

    public User findById(int id) {
        return null;
    }
}
