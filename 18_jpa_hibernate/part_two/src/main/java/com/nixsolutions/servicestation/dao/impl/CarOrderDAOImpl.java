package com.nixsolutions.servicestation.dao.impl;

import com.nixsolutions.servicestation.dao.CarOrderDAO;
import com.nixsolutions.servicestation.entity.CarOrder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by rybkinrolla on 04.01.2016.
 */
public class CarOrderDAOImpl extends GenericAbstractDAO<CarOrder> implements CarOrderDAO {
    public static Logger LOGGER = LogManager.getLogger(CarOrderDAOImpl.class.getName());

    public Set<CarOrder> getUserCarOrders(String login) {
        Set<CarOrder> carOrderList;
        Session session = sFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(CarOrder.class, "co");
        criteria.createAlias("co.car", "car");
        criteria.createAlias("co.carOrderStatus", "carOrderStatus");
        criteria.createAlias("car.carType", "carType");
        criteria.createAlias("car.client", "client");
        criteria.createAlias("client.user", "user");
        criteria.add(Restrictions.eq("user.login", login));
        carOrderList = new HashSet<CarOrder>(criteria.list());
        transaction.commit();
        LOGGER.trace(carOrderList.size() + "rows in car_order were found");
        return carOrderList;
    }

    public Set<CarOrder> getUserCarOrders() {
        Set<CarOrder> carOrderList;
        Session session = sFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(CarOrder.class, "co");
        criteria.createAlias("co.car", "car");
        criteria.createAlias("co.carOrderStatus", "carOrderStatus");
        criteria.createAlias("car.carType", "carType");
        criteria.createAlias("car.client", "client");
        criteria.createAlias("client.user", "user");
        carOrderList = new HashSet<CarOrder>(criteria.list());
        transaction.commit();
        session.close();
        return carOrderList;
    }
}
