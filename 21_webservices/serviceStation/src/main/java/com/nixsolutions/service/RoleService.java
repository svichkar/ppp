package com.nixsolutions.service;

import java.sql.SQLException;
import java.util.List;

import com.nixsolutions.entity.UserRole;

/**
 * @author mixeyes
 *
 */
public interface RoleService {

	/**
	 * getUserRole by userID
	 * 
	 * @param user_role_id
	 * @return UserRoleobject
	 * @throws SQLException
	 */
	UserRole getUserRole(Integer userRoleId);

	/**
	 * createNewUserRole
	 * 
	 * @param model
	 * @throws SQLException
	 */
	void createNewUserRole(UserRole userRole);

	UserRole getUserRole(String userRoleName);

	List<UserRole> getAllRoles();
}
