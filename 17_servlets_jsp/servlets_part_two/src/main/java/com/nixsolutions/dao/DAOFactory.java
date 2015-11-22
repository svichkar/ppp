package com.nixsolutions.dao;

public interface DAOFactory {
	
	public CarDAO getCarDAO();
	
	public CustomerDAO getCustomerDAO();
	
	public OrderInWorkDAO getOrderInWorkDAO();
	
	public OrderPartDAO getOrderPartDAO();
	
	public OrderStatusDAO getOrderStatusDAO();
	
	public OrderWorkerDAO getOrderWorkerDAO();
	
	public PartDAO getPartDAO();
	
	public StatusDAO getStatusDAO();
	
	public WorkerDAO getWorkerDAO();
	
	public WorkerSpecializationDAO getWorkerSpecializationDAO();
	
	public RoleDAO getRoleDAO();
	
	public UserDAO getUserDAO();
}
