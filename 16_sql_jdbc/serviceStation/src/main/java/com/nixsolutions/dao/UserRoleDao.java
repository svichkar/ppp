/**
 * 
 */
package com.nixsolutions.dao;

import java.sql.SQLException;

import com.nixsolutions.entity.UserRole;

/**
 * @author ������
 *
 */
public interface UserRoleDao extends DBTables {

	/**
	 * getUserRole by userID
	 * 
	 * @param user_role_id
	 * @return UserRoleobject
	 * @throws SQLException
	 */
	public UserRole getUserRole(Integer user_role_id) throws SQLException;

	/**
	 * createNewUserRole
	 * 
	 * @param model
	 * @throws SQLException
	 */
	public void createNewUserRole(String model) throws SQLException;
}
