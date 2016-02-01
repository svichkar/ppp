package com.nixsolutions.dao;

import com.nixsolutions.entities.Role;

public interface RoleDAO<T> extends GenericDao<T> {

	public Role findRoleByName(String rolename);
}
