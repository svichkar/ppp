package com.nixsolutions.studentgrade.service;

import com.nixsolutions.studentgrade.model.User;

import java.util.List;

/**
 * Created by svichkar on 1/27/2016.
 */
public interface UserService {

    public void create(User user);

    public void update(User user);

    public void delete(User user);

    public List<User> findAll();

    public User findById(Long id);

    public boolean validateUser(String user);

    public User getUserByLoginAndPassword(String login, String pass);

    public User findByLogin(String login);

    public boolean isUnique(User user);
}
