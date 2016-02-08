package com.nixsolutions.studentgrade.service;

import com.nixsolutions.studentgrade.model.User;

import java.util.List;

/**
 * Created by svichkar on 1/27/2016.
 */
public interface UserService {

    void create(User user);

    void update(User user);

    void delete(User user);

    List<User> findAll();

    User findById(Long id);

    boolean validateUser(String user);

    User getUserByLoginAndPassword(String login, String pass);

    User findByLogin(String login);

    boolean isUnique(User user);
}
