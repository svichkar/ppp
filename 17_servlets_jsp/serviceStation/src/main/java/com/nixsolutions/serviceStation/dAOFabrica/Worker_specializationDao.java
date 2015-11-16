package com.nixsolutions.serviceStation.dAOFabrica;

import java.util.List;

import com.nixsolutions.serviceStation.dbCommon.DBTables;
import com.nixsolutions.serviceStation.dbObjects.Worker_specialization;

public interface Worker_specializationDao extends DBTables {
	/**
	 * \ create New Specialization
	 */
	public void createNewSpecialization(String specialization);

	/**
	 * get All Specialization
	 */
	public List<Worker_specialization> getAllSpecialization();

	/**
	 * delete Specialization By Name
	 */
	public void deleteSpecializationByName(String specialization);


}
