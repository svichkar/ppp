package com.nixsolutions.studentgrade.dao;

import com.nixsolutions.studentgrade.entity.Role;

import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public interface RoleDao {

    public boolean create(Role role);

    public int update(Role role, Role newRole);

    public int delete(Role role);

    public List<Role> findAll();

    public Role findById(int id);


}
