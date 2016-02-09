package com.nixsolutions.dao;

import com.nixsolutions.entities.Role;

public interface RoleDAO extends GenericDao<Role> {

	public Role findRoleByName(String rolename);
}
