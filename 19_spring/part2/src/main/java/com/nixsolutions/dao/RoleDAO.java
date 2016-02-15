package com.nixsolutions.dao;

import com.nixsolutions.entities.Role;

public interface RoleDAO extends GenericDao<Role> {

	Role findRoleByName(String roleName);
}
