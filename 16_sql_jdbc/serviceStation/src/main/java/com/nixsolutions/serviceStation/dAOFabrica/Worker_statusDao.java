/**
 * 
 */
package com.nixsolutions.serviceStation.dAOFabrica;

import com.nixsolutions.serviceStation.dbCommon.DBTables;

/**
 * @author mixeyes
 *
 */
public interface Worker_statusDao extends DBTables {
	public String getWorkerStatus(String last_name, String first_name);
	public void setStatusToWorker(String last_name, String first_name, String status);
}
