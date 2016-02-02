package com.nixsolutions.spring.dao;

import com.nixsolutions.spring.entity.Role;

/**
 * Created by kozlovskij on 12/28/2015.
 */
public interface RoleDAO extends GenericDAO<Role> {
    Role findByName(String name);
}
