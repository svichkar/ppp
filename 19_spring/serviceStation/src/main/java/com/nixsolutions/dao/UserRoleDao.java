/**
 * 
 */
package com.nixsolutions.dao;

import java.sql.SQLException;
import java.util.List;

import com.nixsolutions.entity.UserRole;

/**
 * @author ������
 *
 */
public interface UserRoleDao {

	/**
	 * getUserRole by userID
	 * 
	 * @param user_role_id
	 * @return UserRoleobject
	 * @throws SQLException
	 */
	public UserRole getUserRole(Integer user_role_id);

	/**
	 * createNewUserRole
	 * 
	 * @param model
	 * @throws SQLException
	 */
	public void createNewUserRole(UserRole user_role);

	public UserRole getUserRole(String user_role_name);

	/**
	 * @return
	 */
	public List<UserRole> getAllRoles();
}
