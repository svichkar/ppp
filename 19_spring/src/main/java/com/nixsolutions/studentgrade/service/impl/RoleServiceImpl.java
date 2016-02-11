package com.nixsolutions.studentgrade.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.studentgrade.dao.RoleDAO;
import com.nixsolutions.studentgrade.entity.Role;
import com.nixsolutions.studentgrade.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleDAO roleDao;

	@Override
	public void createRole(Role role) {
		roleDao.createRole(role);
	}

	@Override
	public void updateRole(Role role) {
		roleDao.updateRole(role);
	}

	@Override
	public void deleteRole(Role role) {
		roleDao.deleteRole(role);
	}

	@Override
	public Role findRoleById(Long roleId) {
		return roleDao.findRoleById(roleId);
	}

	@Override
	public Role findRoleByName(String roleName) {
		return roleDao.findRoleByName(roleName);
	}

	@Override
	public List<Role> findAllRoles() {
		return roleDao.findAllRoles();
	}

}
