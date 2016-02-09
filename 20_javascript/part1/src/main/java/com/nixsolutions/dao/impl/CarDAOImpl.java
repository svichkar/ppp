package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.dao.CarDAO;
import com.nixsolutions.entities.Car;

@Repository
@Transactional
public class CarDAOImpl implements CarDAO {

	private final static Logger LOG = LogManager.getLogger(CarDAOImpl.class);
	@Autowired
	protected SessionFactory sessionFactory;

	@Override
	public void create(Car t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public void update(Car t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public void delete(Car t) {
		sessionFactory.getCurrentSession().delete(t);
	}

	@Override
	public Car findByPK(long id) {
		return (Car) sessionFactory.getCurrentSession().get(Car.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Car> getAll() {
		List<Car> cars = new ArrayList<>();
		cars.addAll(sessionFactory.getCurrentSession().createCriteria(Car.class).list());
		return cars;
	}

}
