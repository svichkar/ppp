package com.nixsolutions.studentgrade.dao;

import com.nixsolutions.studentgrade.entity.Role;

import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public interface RoleDao {

    public Role create(Role role);

    public boolean update(Role role);

    public boolean delete(Role role);

    public List<Role> findAll();

    public Role findById(int id);

    public Role findByName(String role);
}
