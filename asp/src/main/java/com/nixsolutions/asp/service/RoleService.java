package com.nixsolutions.asp.service;

import java.util.List;
import java.util.Map;

import com.nixsolutions.asp.entity.Role;

public interface RoleService {
	void create(Role role);

	void update(Role role);

	void delete(Role role);

	Role getByRoleId(int roleId);

    Role getByRoleName(String roleName);

	List<Role> getAll();
	
	Map<String, String> getRoleMap();
}
