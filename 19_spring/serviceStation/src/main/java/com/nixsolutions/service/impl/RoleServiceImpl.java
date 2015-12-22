/**
 * 
 */
package com.nixsolutions.service.impl;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.service.RoleService#getUserRole(java.lang.Integer)
	 */
	@Override
	public UserRole getUserRole(Integer user_role_id) {
		return roleDao.getUserRole(user_role_id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.RoleService#createNewUserRole(com.nixsolutions.
	 * entity.UserRole)
	 */
	@Override
	public void createNewUserRole(UserRole user_role) {
		roleDao.createNewUserRole(user_role);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.service.RoleService#getUserRole(java.lang.String)
	 */
	@Override
	public UserRole getUserRole(String user_role_name) {
		return roleDao.getUserRole(user_role_name);
	}

}
