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
public interface PartDao {

	/**
	 * get all parts in db
	 * 
	 * @return collection of the parts
	 * @throws SQLException
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
	 * @throws SQLException
	 */
	public Part getPart(String partName, String vendor);

	/**
	 * add new part
	 * 
	 * @throws SQLException
	 */
	public void addNewPart(String partName, String vendor, Integer amount);

	/**
	 * delete part
	 * 
	 * @param part_id
	 *            part_id
	 * @return 
	 * @throws SQLException
	 */
	public boolean deletePartByID(Integer part_id);

	/**
	 * updateExistingPart
	 * 
	 * @param part
	 * @throws SQLException
	 */
	public void updateExistingPart(Part part);

	public Part getPart(Integer part_id);
}
