package com.nixsolutions.studentgrade.dao;

import com.nixsolutions.studentgrade.entity.User;

import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public interface UserDao {

    public boolean create(User user);

    public int update(User user, User newUser);

    public int delete(User use);

    public List<User> findAll();

    public User findById(int id);
}
