/**
 * 
 */
package com.nixsolutions.serviceStation.dAOFabrica;

import com.nixsolutions.serviceStation.dbCommon.DBTables;

/**
 * @author Михаил
 *
 */
public interface UserDao extends DBTables {
	/**
	 * validate user
	 * 
	 * @param login
	 * @param password
	 * @return isValid
	 */
	public boolean validate(String login, String password);
}
