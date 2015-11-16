/**
 * 
 */
package com.nixsolutions.serviceStation.dAOFabrica;

import com.nixsolutions.serviceStation.dbCommon.DBTables;
import com.nixsolutions.serviceStation.dbObjects.UserRole;

/**
 * @author Михаил
 *
 */
public interface UserRoleDao extends DBTables {

	/**
	 * getUserRole by userID
	 * 
	 * @param user_role_id
	 * @return UserRoleobject
	 */
	public UserRole getUserRole(Integer user_role_id);
}
