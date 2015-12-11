package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.entity.Role;

public interface RoleBo {
	public void create(Role role);

	public void update(Role role);

	public void delete(Role role);

	public Role getByRoleId(int roleId);

    public Role getByRoleName(String roleName);

	public List<Role> getAll();
}
