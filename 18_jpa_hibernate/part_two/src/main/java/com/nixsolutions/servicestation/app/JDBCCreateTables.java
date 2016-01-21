package com.nixsolutions.servicestation.app;

import com.nixsolutions.servicestation.dao.FactoryDAO;
import com.nixsolutions.servicestation.dao.impl.FactoryDAOImpl;
import com.nixsolutions.servicestation.entity.User;
import com.nixsolutions.servicestation.util.HiberUtil;

/**
 * Created by rybkinrolla on 20.01.2016.
 */
public class JDBCCreateTables {

    public static void main(String[] args) throws ClassNotFoundException {
        HiberUtil.getSessionFactory();
        FactoryDAO factoryDAO = new FactoryDAOImpl();
        User user = factoryDAO.getUserDAO().findByLogin("mor");
        System.out.println(user.getRole().getRoleId());
        HiberUtil.closeSessionFactory();
    }
}
