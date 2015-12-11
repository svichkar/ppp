package com.nixsolutions.dao.impl;

import java.util.List;

import com.nixsolutions.dao.RoleDao;
import com.nixsolutions.entity.Role;

public class RoleDaoImpl extends AbstractDaoImpl implements RoleDao {

    @Override
    public Boolean create(Role role) {
	return super.insert(role);
    }

    @Override
    public Boolean update(Role role) {
	return super.update(role);
    }

    @Override
    public Boolean delete(Role role) {
	return super.delete(role);
    }

    @Override
    public Role getRoleById(Integer roleId) {
	Role r = new Role();
	r.setRoleId(roleId);
	List<Role> roleList = super.findBySeveralFields(r, new String[]{"roleId"});
	if (roleList.size() > 0) {
	    return roleList.get(0);
	} else {
	    return null;
	}
    }

    @Override
    public Role getRoleByName(String roleName) {
	Role r = new Role();
	r.setRoleName(roleName);
	List<Role> roleList = super.findBySeveralFields(r, new String[]{"roleName"});
	if (roleList.size() > 0) {
	    return roleList.get(0);
	} else {
	    return null;
	}
    }

    @Override
    public List<Role> getAll() {
	return super.findBySeveralFields(new Role(), null);
    }

}
