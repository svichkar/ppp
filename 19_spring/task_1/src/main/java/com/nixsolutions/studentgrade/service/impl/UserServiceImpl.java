package com.nixsolutions.studentgrade.service.impl;

import com.nixsolutions.studentgrade.dao.UserDao;
import com.nixsolutions.studentgrade.model.User;
import com.nixsolutions.studentgrade.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by svichkar on 1/28/2016.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    @Qualifier("userDao")
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void create(User user) {

        userDao.create(user);
    }

    @Override
    public void update(User user) {

        userDao.update(user);
    }

    @Override
    public void delete(User user) {

        userDao.delete(user);
    }

    @Override
    public List<User> findAll() {

        return userDao.findAll();
    }

    @Override
    public User findById(Long id) {

        return userDao.findById(id);
    }

    @Override
    public boolean validateUser(String login) {

        return userDao.validateUser(login);
    }

    @Override
    public User getUserByLoginAndPassword(String login, String pass) {

        return userDao.getUserByLoginAndPassword(login, pass);
    }

    @Override
    public User findByLogin(String login) {

        return userDao.findByLogin(login);
    }

    @Override
    public boolean isUnique(User user) {

        return userDao.isUnique(user);
    }
}
