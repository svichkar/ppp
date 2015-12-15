package com.nixsolutions.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.dao.CarDAO;
import com.nixsolutions.hibernate.entity.Car;

@Repository("carDAO")
@Transactional
public class CarDAOImpl implements CarDAO {

	public static Logger LOG = LogManager.getLogger(CarDAOImpl.class.getName());
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void createFrom(Car entity) {
		try {
		sessionFactory.getCurrentSession().saveOrUpdate("car", entity);
		} catch (Exception ex) {
			LOG.error(ex);
		}
	}

	@Override
	public void update(Car entity) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate("car", entity);
		} catch (Exception ex) {
			LOG.error(ex);
		}
	}

	@Override
	public void delete(Car entity) {
		try {
			sessionFactory.getCurrentSession().delete("car", entity);
		} catch (Exception ex) {
			LOG.error(ex);
		}
	}

	@Override
	public Car getByPK(int id) {
		return (Car) sessionFactory.getCurrentSession().byId(Car.class).load(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Car> getAll() {
		return sessionFactory.getCurrentSession().createCriteria(Car.class).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Car> getCarsByCustomerId(int customerId) {
		return sessionFactory.getCurrentSession().createCriteria(Car.class)
				.add(Restrictions.eq("customer.customerId", customerId))
				.list();
	}
}
