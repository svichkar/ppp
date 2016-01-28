package com.nixsolutions.studentgrade.dao;

import com.nixsolutions.studentgrade.model.User;

import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public interface UserDao {

    public void create(User user);

    public void update(User user);

    public void delete(User user);

    public List<User> findAll();

    public User findById(Long id);

    public boolean validateUser(String user);

    public User getUserByLoginAndPassword(String login, String pass);

    public User findByLogin(String login);
}
