package com.nixsolutions.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.dao.CarDAO;
import com.nixsolutions.dao.CustomerDAO;
import com.nixsolutions.dao.DAOFactory;
import com.nixsolutions.dao.OrderInWorkDAO;
import com.nixsolutions.dao.OrderPartDAO;
import com.nixsolutions.dao.OrderStatusDAO;
import com.nixsolutions.dao.OrderWorkerDAO;
import com.nixsolutions.dao.PartDAO;
import com.nixsolutions.dao.RoleDAO;
import com.nixsolutions.dao.StatusDAO;
import com.nixsolutions.dao.UserDAO;
import com.nixsolutions.dao.WorkerDAO;
import com.nixsolutions.dao.WorkerSpecializationDAO;

public class DAOFactoryImpl implements DAOFactory {
	
	public static Logger LOG = LogManager.getLogger(DAOFactoryImpl.class.getName());

	@Override
	public CarDAO getCarDAO() {
		return new CarDAOImpl();
	}

	@Override
	public CustomerDAO getCustomerDAO() {
		return new CustomerDAOImpl();
	}

	@Override
	public OrderInWorkDAO getOrderInWorkDAO() {
		return new OrderInWorkDAOImpl();
	}

	@Override
	public OrderPartDAO getOrderPartDAO() {
		return new OrderPartDAOImpl();
	}

	@Override
	public OrderStatusDAO getOrderStatusDAO() {
		return new OrderStatusDAOImpl();
	}

	@Override
	public OrderWorkerDAO getOrderWorkerDAO() {
		return new OrderWorkerDAOImpl();
	}

	@Override
	public PartDAO getPartDAO() {
		return new PartDAOImpl();
	}

	@Override
	public StatusDAO getStatusDAO() {
		return new StatusDAOImpl();
	}

	@Override
	public WorkerDAO getWorkerDAO() {
		return new WorkerDAOImpl();
	}

	@Override
	public WorkerSpecializationDAO getWorkerSpecializationDAO() {
		return new WorkerSpecializationDAOImpl();
	}

	@Override
	public RoleDAO getRoleDAO() {
		return new RoleDAOImpl();
	}

	@Override
	public UserDAO getUserDAO() {
		return new UserDAOImpl();
	}

}
