package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.Role;

public interface RoleDao {

    public void create(String roleName);

    public void update(Role role);

    public void delete(Role role);

    public Role getRoleById(int roleId);

    public Role getRoleByName(String roleName);

    public List<Role> getAll();
}
