
package com.nixsolutions.dao;

import com.nixsolutions.dao.impl.CarDaoImpl;
import com.nixsolutions.dao.impl.CustomerDaoImpl;
import com.nixsolutions.dao.impl.OrderInWorkDaoImpl;
import com.nixsolutions.dao.impl.OrderStatusDaoImpl;
import com.nixsolutions.dao.impl.OrderWorkerDaoImpl;
import com.nixsolutions.dao.impl.PartDaoImpl;
import com.nixsolutions.dao.impl.PartOrderDaoImpl;
import com.nixsolutions.dao.impl.UserDaoImpl;
import com.nixsolutions.dao.impl.UserRoleDaoImpl;
import com.nixsolutions.dao.impl.WorkerDaoImpl;
import com.nixsolutions.dao.impl.WorkerSpecializationDaoImpl;
import com.nixsolutions.dao.impl.WorkerStatusDaoImpl;

/**
 * @author mixeyes
 *
 */
public class DaoFactory {

	public static CarDao getCarDao() {
		return new CarDaoImpl();
	}

	public static CustomerDao getCustomerDao() {
		return new CustomerDaoImpl();
	}

	public static OrderInWorkDao getOrderInWorkDao() {
		return new OrderInWorkDaoImpl();
	}

	public static OrderStatusDao getOrderStatusDao() {
		return new OrderStatusDaoImpl();
	}

	public static OrderWorkerDao getOrderWorkerDao() {
		return new OrderWorkerDaoImpl();
	}

	public static PartDao getPartDao() {
		return new PartDaoImpl();
	}

	public static PartOrderDao getPartOrderDao() {
		return new PartOrderDaoImpl();
	}

	public static WorkerDao getWorkerDao() {
		return new WorkerDaoImpl();
	}

	public static WorkerSpecializationDao getWorkerSpecializationDao() {
		return new WorkerSpecializationDaoImpl();
	}

	public static WorkerStatusDao getWorkerStatusDao() {
		return new WorkerStatusDaoImpl();
	}

	public static UserDao getUserDao() {
		return new UserDaoImpl();
	}

	public static UserRoleDao getUserRoleDao() {
		return new UserRoleDaoImpl();
	}
}
