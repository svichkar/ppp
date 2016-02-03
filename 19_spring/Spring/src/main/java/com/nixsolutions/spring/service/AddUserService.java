package com.nixsolutions.spring.service;

import com.nixsolutions.spring.dao.RoleDAO;
import com.nixsolutions.spring.dao.UserDAO;
import com.nixsolutions.spring.entity.Role;
import com.nixsolutions.spring.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kozlovskij on 2/3/2016.
 */
@Service
public class AddUserService {
    @Autowired
    UserDAO userDAO;
    @Autowired
    RoleDAO roleDAO;

    public List<Role> roles(){
        return roleDAO.findAll();
    }

    public List<User> users(){
        return userDAO.findAll();
    }
    public void edit (Long userId, String userLogin, String userPassword, Long roleId) {
        User user  = userDAO.findByID(userId);
        user.setLogin(userLogin);
        user.setPassword(userPassword);
        user.setRole(roleDAO.findByID(roleId));
        userDAO.update(user);
    }
    public Long add (String userLogin, String userPassword, Long roleId) {
        User user  = new User();
        user.setLogin(userLogin);
        user.setPassword(userPassword);
        user.setRole(roleDAO.findByID(roleId));
        userDAO.create(user);
        return user.getUserId();
    }
    public void delete (Long userId) {
        userDAO.delete(userDAO.findByID(userId));
    }
}
