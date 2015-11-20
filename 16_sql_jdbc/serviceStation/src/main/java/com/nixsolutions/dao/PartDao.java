/**
 * 
 */
package com.nixsolutions.dao;

import java.sql.SQLException;
import java.util.List;

import com.nixsolutions.entity.Part;

/**
 * @author mixeyes
 *
 */
public interface PartDao extends DBTables {

	/**
	 * get all parts in db
	 * 
	 * @return collection of the parts
	 * @throws SQLException
	 */
	public List<Part> getAllParts() throws SQLException;

	/**
	 * get part
	 * 
	 * @param partName
	 *            sqllab.part name
	 * @param vendor
	 *            vendor
	 * 
	 * @return sqllab.part object
	 * @throws SQLException
	 */
	public Part getPart(String partName, String vendor) throws SQLException;

	/**
	 * add new part
	 * 
	 * @throws SQLException
	 */
	public void addNewPart(String partName, String vendor, Integer amount) throws SQLException;

	/**
	 * delete part
	 * 
	 * @param part_id
	 *            part_id
	 * @throws SQLException
	 */
	public void deletePartByID(Integer part_id) throws SQLException;

	/**
	 * updateExistingPart
	 * 
	 * @param part
	 * @throws SQLException
	 */
	public void updateExistingPart(Part part) throws SQLException;
}
