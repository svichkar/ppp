package com.nixsolutions.asp.dao;

import java.util.List;

import com.nixsolutions.asp.entity.Role;

public interface RoleDao {
	
	void create(Role role);

	void update(Role role);

	void delete(Role role);

	Role getByRoleId(int roleId);

    Role getByRoleName(String roleName);

	List<Role> getAll();
}
