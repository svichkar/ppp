package com.nixsolutions.service;

import java.sql.SQLException;
import java.util.List;

import com.nixsolutions.entity.Part;

public interface PartService {

	/**
	 * get all parts in db
	 * 
	 * @return collection of the parts
	 * @throws SQLException
	 */
	List<Part> getAllParts();

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
	Part getPart(String partName, String vendor);

	/**
	 * add new part
	 * 
	 * @throws SQLException
	 */
	void addNewPart(String partName, String vendor, Integer amount);

	/**
	 * delete part
	 * 
	 * @param partId
	 *            partId
	 * @return
	 * @throws SQLException
	 */
	boolean deletePartByID(Integer partId);

	/**
	 * updateExistingPart
	 * 
	 * @param part
	 * @throws SQLException
	 */
	void updateExistingPart(Part part);

	/**
	 * @param partId
	 * @return
	 */
	Part getPart(Integer partId);

	/**
	 * @param partId
	 * @return
	 */
	Part getPart(String partId);

	/**
	 * @param partId
	 * @param newAmount
	 */
	void changeAmount(String partId, String newAmount);

	/**
	 * @param orderId
	 * @param partId
	 * @param newAmount
	 */
	public void changeAmount(String orderId, String partId, String newAmount);

	void updateExistingPart(String part_id, String partName, String vendor, String amount);

	void deletePartByID(String part_id);
}
