package com.nixsolutions.hibernate.dao;

import com.nixsolutions.hibernate.entity.Role;

/**
 * Created by kozlovskij on 12/28/2015.
 */
public interface RoleDAO extends GenericDAO<Role> {
    Role findByName(String name);
}
