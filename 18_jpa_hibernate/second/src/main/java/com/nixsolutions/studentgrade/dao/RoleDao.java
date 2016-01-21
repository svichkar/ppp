package com.nixsolutions.studentgrade.dao;

import com.nixsolutions.studentgrade.entity.Role;

import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public interface RoleDao {

    public void create(Role role);

    public void update(Role role);

    public void delete(Role role);

    public List<Role> findAll();

    public Role findById(Long id);

    public Role findByName(String role);
}
