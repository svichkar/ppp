package com.nixsolutions.servicestation.service;

import com.nixsolutions.servicestation.entity.User;

/**
 * Created by rybkinrolla on 06.01.2016.
 */
public interface UserService extends GenericService<User> {

    User findByLogin(String login);
}
