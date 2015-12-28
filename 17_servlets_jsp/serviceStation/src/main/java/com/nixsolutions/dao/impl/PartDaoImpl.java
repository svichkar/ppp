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

	private final static Logger LOGGER = LogManager.getLogger();

	@Override
	public void createNewTable() {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "CREATE TABLE sqllab.part( " + 
					"part_id INT IDENTITY, " + 
					"part_name VARCHAR(128) NOT NULL, "+
					"vendor VARCHAR(128) NOT NULL, " + 
					"amount TINYINT);";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			int set = stmt.executeUpdate();
			if (set == 1)
				LOGGER.trace("Table sqllab.part was created");
			else
				LOGGER.debug("Table sqllab.part was not created");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public void deleteTableWithAllData() {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "DROP TABLE sqllab.part;";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			int set = stmt.executeUpdate();
			if (set == 1)
				LOGGER.trace(" table sqllab.part was deleted");
			else
				LOGGER.debug("table sqllab.part was not deleted");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public List<Part> getAllParts() {
		List<Part> parts = new ArrayList<Part>();
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "SELECT * FROM sqllab.part";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet set = stmt.executeQuery();
			LOGGER.trace("Generate list of the sqllab.part objects");
			while (set.next()) {
				parts.add(new Part(set.getInt("part_id"), set.getString("partName"), set.getString("vendor"),
						set.getInt("amount")));
			}
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return parts;
	}

	@Override
	public Part getPart(String partName, String vendor) {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "SELECT * FROM sqllab.part WHERE partName=? AND vendor=?;";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, partName);
			stmt.setString(2, vendor);
			ResultSet set = stmt.executeQuery();
			while (set.next()) {
				return new Part(set.getInt("part_id"), set.getString("partName"), set.getString("vendor"),
						set.getInt("amount"));
			}
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public void addNewPart(String partName, String vendor, Integer amount) {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "INSERT INTO sqllab.part (part_name  ,vendor ,amount)" + "VALUES(?,?,?);";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, partName);
			stmt.setString(2, vendor);
			stmt.setInt(3, amount);
			int set = stmt.executeUpdate();
			if (set == 1)
				LOGGER.trace("New sqllab.part was created");
			else
				LOGGER.debug("New sqllab.part was not created");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public void deletePartByID(Integer part_id) {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "DELETE FROM sqllab.part WHERE part_id=?";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, part_id);
			int set = stmt.executeUpdate();
			if (set == 1)
				LOGGER.trace("part was deleted");
			else
				LOGGER.debug("part was not deleted");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public void updateExistingPart(Part part) {
		try (Connection connection = ConnectionManager.getConnection()) {
			String query = "UPDATE sqllab.part " + "SET partName=?, vendor =?, amount =? where part_id =?;";
			LOGGER.trace("Send query \"" + query + "\"");

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, part.getPartName());
			stmt.setString(2, part.getVendor());
			stmt.setInt(3, part.getAmount());
			stmt.setInt(4, part.getPartId());
			int set = stmt.executeUpdate();
			if (set == 1)
				LOGGER.trace("New sqllab.part was created");
			else
				LOGGER.debug("New sqllab.part was not created");
			stmt.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}
