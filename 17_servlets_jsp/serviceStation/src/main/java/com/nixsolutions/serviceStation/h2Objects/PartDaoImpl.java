/**
 * 
 */
package com.nixsolutions.serviceStation.h2Objects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.serviceStation.dAOFabrica.PartDao;
import com.nixsolutions.serviceStation.dbObjects.Part;

/**
 * @author mixeyes
 *
 */
public class PartDaoImpl implements PartDao {

	private final static Logger logger = LogManager.getLogger();
	private Connection dbConnector;

	
	public PartDaoImpl(Connection dbConnector) {
		this.dbConnector = dbConnector;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dbCommon.DBTables#createNewTable()
	 */
	@Override
	public void createNewTable() {
		try {
			logger.debug("Create DB connector");
			logger.trace("Send query \"CREATE TABLE part( " + "part_id INT IDENTITY, "
					+ "part_name VARCHAR(128) NOT NULL, " + "vendor VARCHAR(128) NOT NULL, " + "amount TINYINT);\"");

			PreparedStatement stmt = dbConnector.prepareStatement(
					"CREATE TABLE sqllab.part( " + "part_id INT IDENTITY, " + "part_name VARCHAR(128) NOT NULL, "
							+ "vendor VARCHAR(128) NOT NULL, " + "amount TINYINT);");
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("Table sqllab.part was created");
			else
				logger.debug("Table sqllab.part was not created");
				stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
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
	public void deleteTableWithAllData() {
		try {
			logger.debug("Create DB connector");
			logger.trace("Send query \"DROP TABLE part;\"");

			PreparedStatement stmt = dbConnector.prepareStatement("DROP TABLE sqllab.part;");
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace(" table sqllab.part was deleted");
			else
				logger.debug("table sqllab.part was not deleted");
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.PartDao#getAllParts()
	 */
	@Override
	public List<Part> getAllParts() {
		List<Part> parts = new ArrayList<Part>();
		try {
			logger.debug("Create DB connector");
			logger.trace("Send query \"SELECT * FROM part\"");
			PreparedStatement stmt = dbConnector.prepareStatement("SELECT * FROM sqllab.part");
			ResultSet set = stmt.executeQuery();
			logger.trace("Generate list of the sqllab.part objects");
			while (set.next()) {
				parts.add(new Part(set.getInt("part_id"), set.getString("part_name"), set.getString("vendor"),
						set.getInt("amount")));
			}
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
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
	public Part getPart(String partName, String vendor) {
		try {
			logger.debug("Create DB connector");
				logger.trace("Send query \"SELECT * FROM sqllab.part WHERE part_name=? AND vendor=?;\"");

			PreparedStatement stmt = dbConnector
					.prepareStatement("SELECT * FROM sqllab.part WHERE part_name=? AND vendor=?;");
			stmt.setString(1, partName);
			stmt.setString(2, vendor);
			ResultSet set = stmt.executeQuery();
			while (set.next()) {
				return new Part(set.getInt("part_id"), set.getString("part_name"), set.getString("vendor"),
						set.getInt("amount"));
			}
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
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
	public void addNewPart(String partName, String vendor, Integer amount) {
		try {
			logger.debug("Create DB connector");
			logger.trace(
					"Send query \"INSERT INTO sqllab.part (part_name  ,vendor ,amount)"
					+ "VALUES(?,?,?);\"");

			PreparedStatement stmt = dbConnector.prepareStatement(""
					+ "INSERT INTO sqllab.part (part_name  ,vendor ,amount)"
					+ "VALUES(?,?,?);");
			stmt.setString(1, partName);
			stmt.setString(2, vendor);
			stmt.setInt(3, amount);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("New sqllab.part was created");
			else
				logger.debug("New sqllab.part was not created");
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
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
	public void deletePartByID(Integer part_id) {
		try {
			logger.debug("Create DB connector");
				logger.trace("Send query \"DELETE FROM sqllab.part WHERE part_id=?\"");

			PreparedStatement stmt = dbConnector.prepareStatement("DELETE FROM sqllab.part WHERE part_id=?");
			stmt.setInt(1, part_id);
			int set = stmt.executeUpdate();
			if (set == 1)
				logger.trace("part was deleted");
			else
				logger.debug("part was not deleted");
			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void updateExistingPart(Part part) {
		try {
			logger.debug("Create DB connector");
			logger.trace(
					"Send query \"INSERT INTO sqllab.part (part_name  ,vendor ,amount)"
					+ "VALUES(?,?,?);\"");

			PreparedStatement stmt = dbConnector.prepareStatement("UPDATE sqllab.part "
					+ "SET part_name=?, vendor =?, amount =? where part_id =?;");
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
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}

	}
}
