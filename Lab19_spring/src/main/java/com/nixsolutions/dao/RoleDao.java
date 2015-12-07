package com.nixsolutions.dao;

import com.nixsolutions.entity.Role;

import java.util.List;

public interface RoleDao {
	public void create(Role role);

	public void update(Role role);

	public void delete(Role role);

	public Role getByRoleId(int roleId);

    public Role getByRoleName(String roleName);

	public List<Role> getAll();
}
