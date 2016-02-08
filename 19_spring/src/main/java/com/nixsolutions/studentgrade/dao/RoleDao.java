package com.nixsolutions.studentgrade.dao;

import com.nixsolutions.studentgrade.model.Role;

import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public interface RoleDao {

    void create(Role role);

    void update(Role role);

    void delete(Role role);

    List<Role> findAll();

    Role findById(Long id);

    Role findByName(String role);
}
