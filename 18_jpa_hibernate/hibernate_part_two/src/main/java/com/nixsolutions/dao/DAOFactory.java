package com.nixsolutions.dao;

public interface DAOFactory {
	
	CarDAO getCarDAO();
	
	CustomerDAO getCustomerDAO();
	
	OrderInWorkDAO getOrderInWorkDAO();
	
	OrderPartDAO getOrderPartDAO();
	
	OrderStatusDAO getOrderStatusDAO();
	
	OrderWorkerDAO getOrderWorkerDAO();
	
	PartDAO getPartDAO();
	
	StatusDAO getStatusDAO();
	
	WorkerDAO getWorkerDAO();
	
	WorkerSpecializationDAO getWorkerSpecializationDAO();
	
	RoleDAO getRoleDAO();
	
	UserDAO getUserDAO();
}
