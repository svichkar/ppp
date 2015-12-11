package com.nixsolutions.dao.impl;

import java.util.List;

import com.nixsolutions.entity.User;
import com.nixsolutions.dao.UserDao;

public class UserDaoImpl extends AbstractDaoImpl implements UserDao {

    @Override
    public Boolean create(User user) {
	return super.insert(user);
    }

    @Override
    public Boolean update(User user) {
	return super.update(user);
    }

    @Override
    public Boolean delete(User user) {
	return super.delete(user);
    }

    @Override
    public User getByUserId(Integer userId) {
	User u = new User();
	u.setUserId(userId);
	List<User> userList = super.findBySeveralFields(u, new String[] {"userId"});
	if (userList.size() > 0) {
	    return userList.get(0);
	} else {
	    return null;
	}
    }

    @Override
    public User getByUserEmail(String email) {
	User u = new User();
	u.setEmail(email);
	List<User> userList = super.findBySeveralFields(u, new String[] {"email"});
	if (userList.size() > 0) {
	    return userList.get(0);
	} else {
	    return null;
	}
    }

    @Override
    public List<User> getAll() {
	return super.findBySeveralFields(new User(), null);
    }
}
