package com.nixsolutions.dao.impl;

import java.util.List;

import com.nixsolutions.entity.User;
import com.nixsolutions.dao.UserDao;

public class UserDaoImpl extends AbstractDaoImpl implements UserDao {
    private static String TABLE_NAME = "user";

    @Override
    public Boolean create(User user) {
	return super.insert(user, User.getMap(), TABLE_NAME);
    }

    @Override
    public Boolean update(User user) {
	return super.update(user, User.getMap(), TABLE_NAME);
    }

    @Override
    public Boolean delete(User user) {
	return super.delete("user_id", user.getUserId(), TABLE_NAME);
    }

    @Override
    public User getByUserId(int userId) {
	List<User> userList = super.find("user_id", String.valueOf(userId), User.getMap(), User.class);
	if (userList.size() > 0) {
	    return userList.get(0);
	} else {
	    return null;
	}
    }

    @Override
    public User getByUserEmail(String email) {
	List<User> userList = super.find("email", email, User.getMap(), User.class);
	if (userList.size() > 0) {
	    return userList.get(0);
	} else {
	    return null;
	}
    }

    @Override
    public List<User> getAll() {
	return super.find(null, null, User.getMap(), User.class);
    }
}
