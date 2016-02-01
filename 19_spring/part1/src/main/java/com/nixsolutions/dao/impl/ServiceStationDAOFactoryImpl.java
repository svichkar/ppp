package com.nixsolutions.dao.impl;

import com.nixsolutions.bean.AllPartsInOrderBean;
import com.nixsolutions.bean.AllWorkersInOrderBean;
import com.nixsolutions.bean.CarCustomerBean;
import com.nixsolutions.bean.OrderInWorkCarStatusBean;
import com.nixsolutions.bean.UserCustomerRoleBean;
import com.nixsolutions.bean.WorkerStatusSpecificationBean;

public class ServiceStationDAOFactoryImpl {

	public static CarDAOImpl getCarDao() {
		return new CarDAOImpl();
	}

	public static CustomerDAOImpl getCustomerDao() {
		return new CustomerDAOImpl();
	}

	public static OrderInWorkDAOImpl getOrderInWorkDao() {
		return new OrderInWorkDAOImpl();
	}

	public static OrderStatusDAOImpl getOrderStatusDao() {
		return new OrderStatusDAOImpl();
	}

	public static OrderWorkerDAOImpl getOrderWorkerDoa() {
		return new OrderWorkerDAOImpl();
	}

	public static PartDAOImpl getPartDao() {
		return new PartDAOImpl();
	}

	public static PartOrderDAOImpl getPartOrderDao() {
		return new PartOrderDAOImpl();
	}

	public static RoleDAOImpl getRoleDao() {
		return new RoleDAOImpl();
	}

	public static UserDAOImpl getUserDao() {
		return new UserDAOImpl();
	}

	public static StatusDAOImpl getStatusDao() {
		return new StatusDAOImpl();
	}

	public static WorkerDAOImpl getWorkerDao() {
		return new WorkerDAOImpl();
	}

	public static WorkerSpecificationDAOImpl getWorkerSpecificationDao() {
		return new WorkerSpecificationDAOImpl();
	}
	
	
	//getting beans
	public static AllPartsInOrderBean getAllPartsInOrderBean()
	{
		return new AllPartsInOrderBean();
	}
	
	public static AllWorkersInOrderBean getAllWorkersInOrderBean()
	{
		return new AllWorkersInOrderBean();
	}
	
	public static CarCustomerBean getCarCustomerBean()
	{
		return new CarCustomerBean();
	}
	
	public static OrderInWorkCarStatusBean getOrderInWorkCarStatusBean()
	{
		return new OrderInWorkCarStatusBean();
	}
	
	public static UserCustomerRoleBean getUserCustomerRoleBean()
	{
		return new UserCustomerRoleBean();
	}
	
	public static WorkerStatusSpecificationBean getWorkerStatusSpecificationBean()
	{
		return new WorkerStatusSpecificationBean();
	}
}
