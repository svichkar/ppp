package com.nixsolutions.studentgrade.service;

import com.nixsolutions.studentgrade.model.Role;

import java.util.List;

/**
 * Created by svichkar on 1/28/2016.
 */
public interface RoleService {

    public void create(Role role);

    public void update(Role role);

    public void delete(Role role);

    public List<Role> findAll();

    public Role findById(Long id);

    public Role findByName(String role);
}
