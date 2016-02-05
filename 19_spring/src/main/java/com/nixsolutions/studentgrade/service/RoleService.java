package com.nixsolutions.studentgrade.service;

import java.util.List;

import com.nixsolutions.studentgrade.entity.Role;

public interface RoleService {

	public void createRole(Role role);

	public void updateRole(Role role);

	public void deleteRole(Role role);

	public Role findRoleById(Long roleId);
	
	public Role findRoleByName(String roleName);

	public List<Role> findAllRoles();

}
