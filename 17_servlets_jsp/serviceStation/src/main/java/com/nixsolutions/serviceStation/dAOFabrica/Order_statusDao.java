package com.nixsolutions.serviceStation.dAOFabrica;

import java.util.List;

import com.nixsolutions.serviceStation.dbCommon.DBTables;
import com.nixsolutions.serviceStation.dbObjects.Order_status;

public interface Order_statusDao extends DBTables{
	/**
	 * \ createNewStatus
	 */
	public void createNewStatus(String status);

	/**
	 * getAllStatus
	 */
	public List<Order_status> getAllStatus();

	/**
	 * deleteStatusByName
	 */
	public void deleteStatusByName(String status);
	
	/**
	 * get Status By ID*/
	public Order_status getStatusByID(Integer status_is);
}
