package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.User;

public interface UserDao {

    public Boolean create(User user);

    public Boolean update(User user);

    public Boolean delete(User user);

    public User getByUserId(int userId);

    public User getByUserEmail(String email);

    public List<User> getAll();
}
