package com.nixsolutions.servicestation.dao.impl;

import com.nixsolutions.servicestation.dao.*;

/**
 * Created by rybkinrolla on 04.01.2016.
 */
public class ImplFactoryDAO implements FactoryDAO {
    @Override
    public CarDAO getCarDAO() {
        return new ImplCarDAO();
    }

    @Override
    public CarOrderDAO getCarOrderDAO() {
        return new ImplCarOrderDAO();
    }

    @Override
    public CarOrderStatusDAO getCarOrderStatusDAO() {
        return new ImplCarOrderStatusDAO();
    }

    @Override
    public CarTypeDAO getCarTypeDAO() {
        return new ImplCarTypeDAO();
    }

    @Override
    public ClientDAO getClientDAO() {
        return new ImplClientDAO();
    }

    @Override
    public EmployeeDAO getEmployeeDAO() {
        return new ImplEmployeeDAO();
    }

    @Override
    public EmployeeCarOrderDAO getEmployeeCarOrderDAO() {
        return new ImplEmployeeCarOrderDAO();
    }

    @Override
    public EmployeeCategoryDAO getEmployeeCategoryDAO() {
        return new ImplEmployeeCategoryDAO();
    }
}
