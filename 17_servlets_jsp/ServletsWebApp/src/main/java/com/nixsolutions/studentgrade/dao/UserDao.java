package com.nixsolutions.studentgrade.dao;

import com.nixsolutions.studentgrade.entity.User;

import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public interface UserDao {

    public User create(User user);

    public boolean update(User user);

    public boolean delete(User use);

    public List<User> findAll();

    public boolean validateUser(String user);

    public User getUserByLoginAndPassword(String login, String pass);
}
