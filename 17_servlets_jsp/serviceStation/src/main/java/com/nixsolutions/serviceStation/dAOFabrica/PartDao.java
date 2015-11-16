/**
 * 
 */
package com.nixsolutions.serviceStation.dAOFabrica;

import java.util.List;

import com.nixsolutions.serviceStation.dbCommon.DBTables;
import com.nixsolutions.serviceStation.dbObjects.Part;

/**
 * @author mixeyes
 *
 */
public interface PartDao extends DBTables {

	/**
	 * get all parts in db
	 * 
	 * @return collection of the parts
	 */
	public List<Part> getAllParts();

	/**
	 * get part
	 * 
	 * @param partName
	 *            sqllab.part name
	 * @param vendor
	 *            vendor
	 * 
	 * @return sqllab.part object
	 */
	public Part getPart(String partName, String vendor);

	/**
	 * add new part
	 */
	public void addNewPart(String partName, String vendor, Integer amount);

	/**
	 * delete part
	 * 
	 * @param part_id
	 *            part_id
	 */
	public void deletePartByID(Integer part_id);
}
