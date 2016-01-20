package com.nixsolutions.servicestation.dao.impl;

import com.nixsolutions.servicestation.dao.*;

/**
 * Created by rybkinrolla on 04.01.2016.
 */
public class FactoryDAOImpl implements FactoryDAO {
    @Override
    public CarDAO getCarDAO() {
        return new CarDAOImpl();
    }

    @Override
    public CarOrderDAO getCarOrderDAO() {
        return new CarOrderDAOImpl();
    }

    @Override
    public CarOrderStatusDAO getCarOrderStatusDAO() {
        return new CarOrderStatusDAOImpl();
    }

    @Override
    public CarTypeDAO getCarTypeDAO() {
        return new CarTypeDAOImpl();
    }

    @Override
    public ClientDAO getClientDAO() {
        return new ClientDAOImpl();
    }

    @Override
    public EmployeeDAO getEmployeeDAO() {
        return new EmployeeDAOImpl();
    }

    @Override
    public EmployeeCategoryDAO getEmployeeCategoryDAO() {
        return new EmployeeCategoryDAOImpl();
    }

    @Override
    public UserDAO getUserDAO() {
        return new UserDAOImpl();
    }

    @Override
    public RoleDAO getRoleDAO() {
        return new RoleDAOImpl();
    }
}
