package com.nixsolutions.serviceStation.dAOFabrica;

import java.util.List;

import com.nixsolutions.serviceStation.dbCommon.DBTables;
import com.nixsolutions.serviceStation.dbObjects.Worker_specialization;

public interface Worker_specializationDao extends DBTables {
	/**
	 * \ createNewStatus
	 */
	public void createNewSpecialization(String specialization);

	/**
	 * getAllStatus
	 */
	public List<Worker_specialization> getAllSpecialization();

	/**
	 * deleteStatusByName
	 */
	public void deleteSpecializationByName(String specialization);


}
