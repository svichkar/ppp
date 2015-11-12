/**
 * 
 */
package com.nixsolutions.serviceStation.h2Objects;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.serviceStation.dbCommon.DbConnector;

/**
 * @author mixeyes
 *
 */
public class ServiceFactory {
	private final static Logger logger = LogManager.getLogger();
	private DbConnector connector = null;

	public ServiceFactory() {
		super();
		try {
			connector = new DbConnector();
		} catch (ClassNotFoundException | SQLException e) {
			logger.catching(e);
		} // TODO Auto-generated constructor stub
	}

	public CarDaoImpl getCarDao(){
		return new CarDaoImpl(connector.getConnection());
	}
}
