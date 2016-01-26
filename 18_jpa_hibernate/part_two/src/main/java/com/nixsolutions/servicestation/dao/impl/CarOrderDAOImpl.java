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
import java.util.Set;

/**
 * Created by rybkinrolla on 04.01.2016.
 */
public class CarOrderDAOImpl extends GenericAbstractDAO<CarOrder> implements CarOrderDAO {
    public static Logger LOGGER = LogManager.getLogger(CarOrderDAOImpl.class.getName());

    public Set<CarOrder> getUserCarOrders(String login) {
        Set<CarOrder> carOrderSet;
        Session session = sFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(CarOrder.class, "co");
        criteria.createAlias("co.car", "car");
        criteria.createAlias("car.client", "client");
        criteria.createAlias("client.user", "user");
        criteria.add(Restrictions.eq("user.login", login));
        carOrderSet = new HashSet<>(criteria.list());
        transaction.commit();
        LOGGER.trace(carOrderSet.size() + " rows in car_order were found");
        return carOrderSet;
    }

    public Set<CarOrder> getUserCarOrders() {
        Set<CarOrder> carOrderSet;
        Session session = sFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(CarOrder.class, "co");
        criteria.createAlias("co.car", "car");
        criteria.createAlias("car.client", "client");
        criteria.createAlias("client.user", "user");
        carOrderSet = new HashSet<>(criteria.list());
        transaction.commit();
        session.close();
        LOGGER.trace(carOrderSet.size() + " rows in car_order were found");
        return carOrderSet;
    }
}
