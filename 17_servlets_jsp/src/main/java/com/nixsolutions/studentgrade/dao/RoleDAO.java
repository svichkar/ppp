package com.nixsolutions.studentgrade.dao;

import java.util.List;

import com.nixsolutions.studentgrade.entity.Role;

public interface RoleDAO {

	public Role createRole(int roleId, String roleName);

	public void updateRole(Role role);

	public void deleteRole(Role role);

	public Role findRoleById(int roleId);
	
	public Role findRoleByName(String roleName);

	public List<Role> findAllRoles();

}
