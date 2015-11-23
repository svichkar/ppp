package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.User;

public interface UserDao {

    public void create(String email, String password, int roleId);

    public void update(User user);

    public void delete(User user);

    public User getByUserId(int userId);

    public User getByUserEmail(String email);

    public List<User> getAll();
}
