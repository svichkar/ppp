package com.nixsolutions.servicestation.dao.impl;

import com.nixsolutions.servicestation.dao.CarDAO;
import com.nixsolutions.servicestation.entity.Car;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by rybkinrolla on 04.01.2016.
 */

@Repository("carDao")
public class CarDAOImpl extends GenericAbstractDAO<Car> implements CarDAO {

    public Set<Car> getCarWithoutOrder() {
        Set<Car> carSet;
        Query query = getCurrentSession().createQuery("from Car as c " +
                "left join fetch c.carOrder as co " +
                "where co.carOrderId is null");
        carSet = new HashSet<>(query.list());
        return carSet;
    }

    public Set<Car> getUserCarOrders(String login) {
        Set<Car> carOrderSet;
        Criteria criteria = getCurrentSession().createCriteria(Car.class, "car");
        criteria.createAlias("car.client", "client");
        criteria.createAlias("client.user", "user");
        criteria.add(Restrictions.eq("user.login", login));
        carOrderSet = new HashSet<>(criteria.list());
        return carOrderSet;
    }
}
