package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.dao.CarDAO;
import com.nixsolutions.entities.Car;

@Repository
@Transactional
public class CarDAOImpl implements CarDAO {

	@Autowired
	protected SessionFactory sessionFactory;

	public void create(Car t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	public void update(Car t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	public void delete(Car t) {
		sessionFactory.getCurrentSession().delete(t);
	}

	public Car findByPK(long id) {
		return (Car) sessionFactory.getCurrentSession().get(Car.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Car> getAll() {
		List<Car> cars = new ArrayList<>();
		cars.addAll(sessionFactory.getCurrentSession().createCriteria(Car.class).list());
		return cars;
	}

	@Override
	public Car findByVin(String vin) {
		return (Car) sessionFactory.getCurrentSession().createCriteria(Car.class).add(Restrictions.eq("vin", vin))
				.uniqueResult();
	}

}
