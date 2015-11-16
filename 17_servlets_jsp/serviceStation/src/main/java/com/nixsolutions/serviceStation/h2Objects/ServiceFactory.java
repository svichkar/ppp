/**
 * 
 */
package com.nixsolutions.serviceStation.h2Objects;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.store.RecoverTester;

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
		} 
	}

	public CarDaoImpl getCarDaoImpl() {
		return new CarDaoImpl(connector.getConnection());
	}

	public CustomerDaoImpl getCustomerDaoImpl() {
		return new CustomerDaoImpl(connector.getConnection());
	}

	public Order_in_workDaoImpl getOrder_in_workDaoImpl() {
		return new Order_in_workDaoImpl(connector.getConnection());
	}

	public Order_statusDaoImpl getOrder_statusDaoImpl() {
		return new Order_statusDaoImpl(connector.getConnection());
	}

	public Order_workerDaoImpl getOrder_workerDaoImpl() {
		return new Order_workerDaoImpl(connector.getConnection());
	}

	public PartDaoImpl getPartDaoImpl() {
		return new PartDaoImpl(connector.getConnection());
	}

	public Part_orderDaoImpl getPart_orderDaoImpl() {
		return new Part_orderDaoImpl(connector.getConnection());
	}

	public WorkerDaoImpl getWorkerDaoImpl() {
		return new WorkerDaoImpl(connector.getConnection());
	}

	public Worker_specializationDaoImpl getWorker_specializationDaoImpl() {
		return new Worker_specializationDaoImpl(connector.getConnection());
	}

	public Worker_statusDaoImpl getWorker_statusDaoImpl() {
		return new Worker_statusDaoImpl(connector.getConnection());
	}

	public UserDaoImpl getUserDaoImpl() {
		return new UserDaoImpl(connector.getConnection());
	}
	
	public UserRoleDaoImpl getUserRoleDaoImpl() {
		return new UserRoleDaoImpl(connector.getConnection());
	}
}
