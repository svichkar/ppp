package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.entities.Role;

public interface RoleService {

	public Role findRoleByid(long id);

	public List<Role> getAllRoles();

	public void addRole(Role role);

	public void updateRole(Role role);

	public void deleteRole(Role role);
}
