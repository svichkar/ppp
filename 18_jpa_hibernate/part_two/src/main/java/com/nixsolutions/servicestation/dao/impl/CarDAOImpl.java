package com.nixsolutions.servicestation.dao.impl;

import com.nixsolutions.servicestation.dao.CarDAO;
import com.nixsolutions.servicestation.entity.Car;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by rybkinrolla on 04.01.2016.
 */
public class CarDAOImpl extends GenericAbstractDAO<Car> implements CarDAO {
    public static Logger LOGGER = LogManager.getLogger(CarOrderDAOImpl.class.getName());

    public Set<Car> getUserCars() {
        Set<Car> carSet;
        Session session = sFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(Car.class, "c");
            criteria.createAlias("c.client", "client");
            criteria.createAlias("client.user", "user");
            carSet = new HashSet<>(criteria.list());
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
        LOGGER.trace(carSet.size() + " rows in car were found");
        return carSet;
    }

    public Set<Car> getCarWithoutOrder() {
        Set<Car> carSet;
        Session session = sFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("from Car as c " +
                    "left join fetch c.carOrder as co " +
                    "where co.carOrderId is null");
            carSet = new HashSet<>(query.list());
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
        LOGGER.trace(carSet.size() + " rows in car by getCarWithoutOrder() were found");
        return carSet;
    }
}
