package com.nixsolutions.servicestation.dao;

/**
 * Created by rybkinrolla on 04.01.2016.
 */
public interface FactoryDAO {
    public CarDAO getCarDAO();

    public CarOrderDAO getCarOrderDAO();

    public CarOrderStatusDAO getCarOrderStatusDAO();

    public CarTypeDAO getCarTypeDAO();

    public ClientDAO getClientDAO();

    public EmployeeDAO getEmployeeDAO();

    public EmployeeCategoryDAO getEmployeeCategoryDAO();

    public UserDAO getUserDAO();

    public RoleDAO getRoleDAO();
}
