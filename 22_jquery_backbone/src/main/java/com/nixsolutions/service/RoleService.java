package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.entities.Role;

public interface RoleService {

	Role findRoleByid(long id);

	List<Role> getAllRoles();

	void addRole(Role role);

	void updateRole(Role role);

	void deleteRole(Role role);
}
