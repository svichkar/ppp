package com.nixsolutions.servicestation.service.impl;

import com.nixsolutions.servicestation.dao.UserDAO;
import com.nixsolutions.servicestation.entity.User;
import com.nixsolutions.servicestation.service.UserService;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by rybkinrolla on 06.01.2016.
 */

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserDAO userDAO;

    @Override
    public User findByLogin(String login) {
        User user = userDAO.findByLogin(login);
        return user;
    }

    @Override
    public void create(User entity) {
        userDAO.create(entity);
    }

    @Override
    public void update(User entity) {
        userDAO.update(entity);
    }

    @Override
    public void delete(User entity) {
        userDAO.delete(entity);
    }

    @Override
    public User findById(Long id) {
        User user = userDAO.findById(id);
        return user;
    }

    @Override
    public Set<User> findAll() {
        Set<User> userSet = userDAO.findAll();
        return userSet;
    }
}
