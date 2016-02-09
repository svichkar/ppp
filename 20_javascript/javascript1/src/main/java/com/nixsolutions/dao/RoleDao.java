package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.Role;

public interface RoleDao {
	public List<Role> getAllRoles();
	public Role getRoleById(Long roleId);
	public void createRole(Role role);
	public void updateRole(Role role);
	public void deleteRole(Role role);
	public Role getRoleByName(String name);
}
