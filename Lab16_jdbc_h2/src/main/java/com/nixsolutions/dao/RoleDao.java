package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.Role;

public interface RoleDao {
	public void create(String roleName);

	public void update(Role role);

	public void delete(Role role);

	public Role getByRoleId(int roleId);
	
	public Role getByRoleName(String roleName);

	public List<Role> getAll();
}
