package com.nixsolutions.servicestation.dao;

import com.nixsolutions.servicestation.entity.User;
import com.nixsolutions.servicestation.entity.extendedentity.UserClientBean;

import java.util.List;

/**
 * Created by rybkinrolla on 06.01.2016.
 */
public interface UserDAO extends JointDAO<User>{

    User findByLogin(String login);
    List<UserClientBean> findClientsUsers();
}
