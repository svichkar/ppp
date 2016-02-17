package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.Role;

public interface RoleDao {
	 List<Role> getAllRoles();
	 Role getRoleById(Long roleId);
	 void createRole(Role role);
	 void updateRole(Role role);
	 void deleteRole(Role role);
	 Role getRoleByName(String name);
}
