/**
 * 
 */
package com.nixsolutions.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.util.ConnectionManager;

/**
 * @author mixeyes
 *
 */
public class DropDB {
	private final static Logger logger = LogManager.getLogger();

	public DropDB() {
		try {
			dropDB();
		} catch (ClassNotFoundException e) {
		}
	}

	/**
	 * @param args
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		dropDB();

	}

	public static void dropDB() throws ClassNotFoundException {

		try (Connection conn= ConnectionManager.getConnection()){
			logger.trace(
					// "Send query \"DROP TABLE sqllab.car ,customer
					// ,order_in_work ,part ,part_order ,status ,worker
					// ,worker_specialization ,worker_status ,order_status
					// ,order_worker;\"");
					"Send query \"DROP SCHEMA sqllab\"");
			PreparedStatement stmt = conn.prepareStatement(
					// "DROP TABLE worker_specialization, worker_status,
					// sqllab.worker , order_in_work, order_status, part,
					// part_order, sqllab.car , customer, sqllab.order_worker
					// ;");
					"DROP SCHEMA sqllab");
			int set = stmt.executeUpdate();
			if (set == 0)
				logger.trace("All tables was deleted");
			else
				logger.debug("All tables was not deleted");

			stmt.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} 
	}

}
