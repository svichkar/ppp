package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.UserDao;
import com.nixsolutions.studentgrade.entity.User;

import java.util.List;

/**
 * Created by svichkar on 12/23/2015.
 */
public class UserDaoImpl implements UserDao {
    public boolean create(User user) {
        return false;
    }

    public int update(User user, User newUser) {
        return 0;
    }

    public int delete(User use) {
        return 0;
    }

    public List<User> findAll() {
        return null;
    }

    public User findById(int id) {
        return null;
    }

    public boolean validateUser(String user) {
        if (user.equals("kos") || user.equals("qwe"))
            return true;
        return false;
    }

    public User getUserByLoginAndPassword(String user, String pass) {

        if (user.equals("kos") && pass.equals("123")) {
            return new User(1, "Konstantin", "Svichkar", "123", "kos", "2012kostyan@gmail.com", 1);
        }
        else if (user.equals("qwe") && pass.equals("123")){
            return new User (2, "Guest", "Nov", "123", "qwe", "123kostyan@gmail.com", 2);
        }
        else {
            return null;
        }
    }

}
