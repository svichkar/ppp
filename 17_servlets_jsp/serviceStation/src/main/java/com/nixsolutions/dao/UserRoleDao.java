/**
 * 
 */
package com.nixsolutions.dao;

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
	 * @return UserRoleobject @
	 */
	public UserRole getUserRole(Integer user_role_id);

	/**
	 * createNewUserRole
	 * 
	 * @param model
	 * @
	 */
	public void createNewUserRole(String model);
}
