package com.nixsolutions.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.RoleDao;
import com.nixsolutions.entity.Role;
import com.nixsolutions.services.RoleBo;

@Service
public class RoleBoImpl implements RoleBo {

	@Autowired
	private RoleDao roleDao;

	@Override
	public void create(Role role) {
		roleDao.create(role);
	}

	@Override
	public void update(Role role) {
		roleDao.update(role);
	}

	@Override
	public void delete(Role role) {
		roleDao.delete(role);
	}

	@Override
	public Role getByRoleId(int roleId) {
		return roleDao.getByRoleId(roleId);
	}

	@Override
	public Role getByRoleName(String roleName) {
		return roleDao.getByRoleName(roleName);
	}

	@Override
	public List<Role> getAll() {		
		return roleDao.getAll();
	}

}
