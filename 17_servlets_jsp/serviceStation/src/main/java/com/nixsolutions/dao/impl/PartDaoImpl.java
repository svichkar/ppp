/**
 * 
 */
package com.nixsolutions.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.dao.PartDao;
import com.nixsolutions.entity.Part;
import com.nixsolutions.util.ConnectionManager;

/**
 * @author mixeyes
 *
 */
public class PartDaoImpl implements PartDao {

	private final static Logger logger = LogManager.getLogger();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dbCommon.DBTables#createNewTable()
	 */
	@Override
	public void createNewTable() throws SQLException {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "CREATE TABLE sqllab.part( " + "part_id INT IDENTITY, " + "part_name VARCHAR(128) NOT NULL, "
					+ "vendor VARCHAR(128) NOT NULL, " + "amount TINYINT);";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("Table sqllab.part was created");
			else
				logger.debug("Table sqllab.part was not created");
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				connection.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dbCommon.DBTables#deleteTableWithAllData(
	 * )
	 */
	@Override
	public void deleteTableWithAllData() throws SQLException {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "DROP TABLE sqllab.part;";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace(" table sqllab.part was deleted");
			else
				logger.debug("table sqllab.part was not deleted");
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				connection.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.PartDao#getAllParts()
	 */
	@Override
	public List<Part> getAllParts() throws SQLException {
		List<Part> parts = new ArrayList<Part>();
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "SELECT * FROM sqllab.part";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the sqllab.part objects");
			while (set.next()) {
				parts.add(new Part(set.getInt("part_id"), set.getString("part_name"), set.getString("vendor"),
						set.getInt("amount")));
			}
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				connection.close();
		}
		return parts;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.PartDao#getPart(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public Part getPart(String partName, String vendor) throws SQLException {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "SELECT * FROM sqllab.part WHERE part_name=? AND vendor=?;";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, partName);
			stmt.setString(2, vendor);
			ResultSet set = stmt.executeQuery();
			while (set.next()) {
				return new Part(set.getInt("part_id"), set.getString("part_name"), set.getString("vendor"),
						set.getInt("amount"));
			}
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				connection.close();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.PartDao#addNewPart(java.lang.
	 * String, java.lang.String, java.lang.Integer)
	 */
	@Override
	public void addNewPart(String partName, String vendor, Integer amount) throws SQLException {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "INSERT INTO sqllab.part (part_name  ,vendor ,amount)" + "VALUES(?,?,?);";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, partName);
			stmt.setString(2, vendor);
			stmt.setInt(3, amount);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("New sqllab.part was created");
			else
				logger.debug("New sqllab.part was not created");
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				connection.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.PartDao#deletePartByID(java.
	 * lang.Integer)
	 */
	@Override
	public void deletePartByID(Integer part_id) throws SQLException {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "DELETE FROM sqllab.part WHERE part_id=?";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, part_id);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("part was deleted");
			else
				logger.debug("part was not deleted");
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				connection.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.dao.PartDao#updateExistingPart(com.nixsolutions.entity.
	 * Part)
	 */
	@Override
	public void updateExistingPart(Part part) throws SQLException {
		Connection connection = null;
		try {
			logger.debug("Create DB connector");
			connection = ConnectionManager.getConnection();
			String query = "UPDATE sqllab.part " + "SET part_name=?, vendor =?, amount =? where part_id =?;";
			logger.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, part.getPart_name());
			stmt.setString(2, part.getVendor());
			stmt.setInt(3, part.getAmount());
			stmt.setInt(4, part.getPart_id());
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("New sqllab.part was created");
			else
				logger.debug("New sqllab.part was not created");
			stmt.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (connection != null)
				connection.close();
		}
	}
}
