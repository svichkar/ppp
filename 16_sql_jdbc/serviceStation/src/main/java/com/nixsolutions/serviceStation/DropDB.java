/**
 * 
 */
package com.nixsolutions.serviceStation;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.serviceStation.dbCommon.DbConnector;

/**
 * @author mixeyes
 *
 */
public class DropDB {
	private final static Logger logger = LogManager.getLogger(DropDB.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DbConnector dbConnector = null;
		try {
			dbConnector = new DbConnector();
			dbConnector.deleteAllTablesWithAllData();
		} catch (ClassNotFoundException | SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
