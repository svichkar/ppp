package com.nixsolutions.servicestation.dao;

import com.nixsolutions.servicestation.entity.EmployeeCarOrder;
import com.nixsolutions.servicestation.entity.EmployeeCategory;

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

    public EmployeeCarOrderDAO getEmployeeCarOrderDAO();

    public EmployeeCategoryDAO getEmployeeCategoryDAO();

    public UserDAO getUserDAO();

    public RoleDAO getRoleDAO();
}
