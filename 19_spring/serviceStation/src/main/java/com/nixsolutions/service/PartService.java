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

	/**
	 * @param part_id
	 * @return
	 */
	public Part getPart(Integer part_id);

	/**
	 * @param part_id
	 * @return
	 */
	public Part getPart(String part_id);

	/**
	 * @param partId
	 * @param newAmount
	 */
	public void changeAmount(String partId, String newAmount);

	/**
	 * @param orderId
	 * @param partId
	 * @param newAmount
	 */
	public void changeAmount(String orderId, String partId, String newAmount);
}
