package com.nixsolutions.studentgrade.service;

import com.nixsolutions.studentgrade.model.Role;

import java.util.List;

/**
 * Created by svichkar on 1/28/2016.
 */
public interface RoleService {

    void create(Role role);

    void update(Role role);

    void delete(Role role);

    List<Role> findAll();

    Role findById(Long id);

    Role findByName(String role);
}
