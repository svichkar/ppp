
package com.nixsolutions.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	private final static Logger logger = LogManager.getLogger();

	public static CarDaoImpl getCarDaoImpl() {
		return new CarDaoImpl();
	}

	public static CustomerDaoImpl getCustomerDaoImpl() {
		return new CustomerDaoImpl();
	}

	public static OrderInWorkDaoImpl getOrderInWorkDaoImpl() {
		return new OrderInWorkDaoImpl();
	}

	public static OrderStatusDaoImpl getOrderStatusDaoImpl() {
		return new OrderStatusDaoImpl();
	}

	public static OrderWorkerDaoImpl getOrderWorkerDaoImpl() {
		return new OrderWorkerDaoImpl();
	}

	public static PartDaoImpl getPartDaoImpl() {
		return new PartDaoImpl();
	}

	public static PartOrderDaoImpl getPartOrderDaoImpl() {
		return new PartOrderDaoImpl();
	}

	public static WorkerDaoImpl getWorkerDaoImpl() {
		return new WorkerDaoImpl();
	}

	public static WorkerSpecializationDaoImpl getWorkerSpecializationDaoImpl() {
		return new WorkerSpecializationDaoImpl();
	}

	public static WorkerStatusDaoImpl getWorkerStatusDaoImpl() {
		return new WorkerStatusDaoImpl();
	}

	public static UserDaoImpl getUserDaoImpl() {
		return new UserDaoImpl();
	}

	public static UserRoleDaoImpl getUserRoleDaoImpl() {
		return new UserRoleDaoImpl();
	}
}
