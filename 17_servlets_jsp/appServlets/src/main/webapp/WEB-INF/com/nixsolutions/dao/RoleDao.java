package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.Role;

public interface RoleDao {

    public Boolean create(Role role);

    public Boolean update(Role role);

    public Boolean delete(Role role);

    public Role getRoleById(int roleId);

    public Role getRoleByName(String roleName);

    public List<Role> getAll();
}
