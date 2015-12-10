package com.nixsolutions.dao.hibernate;

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

public class DaoFactoryHibernate implements DAOFactory {
	
	public static Logger LOG = LogManager.getLogger(DaoFactoryHibernate.class.getName());

	@Override
	public CarDAO getCarDAO() {
		return new CarDaoHibernate();
	}

	@Override
	public CustomerDAO getCustomerDAO() {
		return new CustomerDaoHibernate();
	}

	@Override
	public OrderInWorkDAO getOrderInWorkDAO() {
		return new OrderInWorkDaoHibernate();
	}

	@Override
	public OrderPartDAO getOrderPartDAO() {
		return new OrderPartDaoHibernate();
	}

	@Override
	public OrderStatusDAO getOrderStatusDAO() {
		return new OrderStatusDaoHibernate();
	}

	@Override
	public OrderWorkerDAO getOrderWorkerDAO() {
		return new OrderWorkerDaoHibernate();
	}

	@Override
	public PartDAO getPartDAO() {
		return new PartDaoHibernate();
	}

	@Override
	public StatusDAO getStatusDAO() {
		return new StatusDaoHibernate();
	}

	@Override
	public WorkerDAO getWorkerDAO() {
		return new WorkerDaoHibernate();
	}

	@Override
	public WorkerSpecializationDAO getWorkerSpecializationDAO() {
		return new WorkerSpecializationDaoHibernate();
	}

	@Override
	public RoleDAO getRoleDAO() {
		return new RoleDaoHibernate();
	}

	@Override
	public UserDAO getUserDAO() {
		return new UserDaoHibernate();
	}

}
