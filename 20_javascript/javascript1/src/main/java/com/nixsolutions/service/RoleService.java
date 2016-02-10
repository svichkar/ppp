package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.entity.Role;

public interface RoleService {
	 List<Role> getAllRoles();
	 Role getRoleById(Long roleId);
	 void createRole(Role role);
	 void updateRole(Role role);
	 void deleteRole(Role role);
	 Role getRoleByName(String name);
}
