package com.nixsolutions.studentgrade.dao;

import com.nixsolutions.studentgrade.entity.User;

import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public interface UserDao {

    public void create(User user);

    public void update(User user);

    public void delete(User use);

    public List<User> findAll();

    public boolean validateUser(String user);

    public User getUserByLoginAndPassword(String login, String pass);

    public User findByLogin(String pageOwner);
}
