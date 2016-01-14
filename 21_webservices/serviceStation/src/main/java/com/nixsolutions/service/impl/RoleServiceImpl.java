/**
 * 
 */
package com.nixsolutions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.UserRoleDao;
import com.nixsolutions.entity.UserRole;
import com.nixsolutions.service.RoleService;

/**
 * @author mixeyes
 *
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private UserRoleDao roleDao;


	@Override
	public List<UserRole> getAllRoles() {
		return roleDao.getAllRoles();
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.service.RoleService#getUserRole(java.lang.Integer)
	 */
	@Override
	public UserRole getUserRole(Integer userRoleId) {
		return roleDao.getUserRole(userRoleId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.RoleService#createNewUserRole(com.nixsolutions.
	 * entity.UserRole)
	 */
	@Override
	public void createNewUserRole(UserRole userRole) {
		roleDao.createNewUserRole(userRole);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.service.RoleService#getUserRole(java.lang.String)
	 */
	@Override
	public UserRole getUserRole(String userRoleName) {
		return roleDao.getUserRole(userRoleName);
	}

}
