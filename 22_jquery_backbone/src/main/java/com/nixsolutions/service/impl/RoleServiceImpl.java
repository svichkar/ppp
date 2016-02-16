package com.nixsolutions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.RoleDAO;
import com.nixsolutions.entities.Role;
import com.nixsolutions.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDAO roleDaoImpl;

	@Override
	public Role findRoleByid(long id) {
		return roleDaoImpl.findByPK(id);
	}

	@Override
	public List<Role> getAllRoles() {
		return roleDaoImpl.getAll();
	}

	@Override
	public void addRole(Role role) {
		roleDaoImpl.create(role);
	}

	@Override
	public void updateRole(Role role) {
		roleDaoImpl.update(role);
	}

	@Override
	public void deleteRole(Role role) {
		roleDaoImpl.delete(role);
	}

}
