package com.nixsolutions.serviceStation.dAOFabrica;

import java.util.List;

import com.nixsolutions.serviceStation.DBTables;
import com.nixsolutions.serviceStation.dbObjects.Status;

public interface StatusDao extends DBTables{
	/**
	 * \ createNewStatus
	 */
	public void createNewStatus(String status);

	/**
	 * getAllStatus
	 */
	public List<Status> getAllStatus();

	/**
	 * deleteStatusByName
	 */
	public void deleteStatusByName(String status);
}
