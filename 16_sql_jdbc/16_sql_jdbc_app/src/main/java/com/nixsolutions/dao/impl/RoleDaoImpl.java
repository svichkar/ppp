package com.nixsolutions.dao.impl;

import java.util.List;

import com.nixsolutions.dao.RoleDao;
import com.nixsolutions.entity.Role;

public class RoleDaoImpl extends AbstractDaoImpl implements RoleDao {
    private static String TABLE_NAME = "role";

    @Override
    public void create(String roleName) {
	super.insert(new String[] { "role" }, new String[] { roleName }, TABLE_NAME);
    }

    @Override
    public void update(Role role) {
	super.update(role, Role.getMap(), TABLE_NAME);
    }

    @Override
    public void delete(Role role) {
	super.delete("role_id", role.getRoleId(), TABLE_NAME);
    }

    @Override
    public Role getRoleById(int roleId) {
	List<Role> roleList = super.find("role_id", String.valueOf(roleId), Role.getMap(), Role.class);
	if (roleList.size() > 0) {
	    return roleList.get(0);
	} else {
	    return null;
	}
    }

    @Override
    public Role getRoleByName(String roleName) {
	List<Role> roleList = super.find("role", String.valueOf(roleName), Role.getMap(), Role.class);
	if (roleList.size() > 0) {
	    return roleList.get(0);
	} else {
	    return null;
	}
    }

    @Override
    public List<Role> getAll() {
	return super.find(null, null, Role.getMap(), Role.class);
    }

}
