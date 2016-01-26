package com.nixsolutions.servicestation.dao.impl;

import com.nixsolutions.servicestation.dao.CarDAO;
import com.nixsolutions.servicestation.entity.Car;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by rybkinrolla on 04.01.2016.
 */
public class CarDAOImpl extends GenericAbstractDAO<Car> implements CarDAO {

    public Set<Car> getUserCars() {
        Set<Car> carList;
        Session session = sFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Car.class, "c");
        criteria.createAlias("c.carType", "carType");
        criteria.createAlias("c.client", "client");
        criteria.createAlias("client.user", "user");
        carList = new HashSet<Car>(criteria.list());
        transaction.commit();
        return carList;
    }

    public Set<Car> getCarWithoutOrder() {
        Set<Car> carList;
        Session session = sFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Car.class, "c");
        criteria.createAlias("c.carType", "carType");
        criteria.createAlias("c.carOrder", "carOrder", JoinType.LEFT_OUTER_JOIN);
        criteria.add(Restrictions.isNull("carOrder.carOrderId"));
        carList = new HashSet<Car>(criteria.list());
        transaction.commit();
        return carList;
    }
}
