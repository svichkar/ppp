package com.nixsolutions.dao.impl;

import java.lang.annotation.Retention;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nixsolutions.app.HibernateUtil;
import com.nixsolutions.dao.CarDAO;
import com.nixsolutions.entities.Car;

public class CarDAOImpl implements CarDAO<Car> {

	private final static Logger LOG = LogManager.getLogger(CarDAOImpl.class);	
	public static SessionFactory sessionFactory;

	public CarDAOImpl() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@Override
	public void create(Car t) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(t);
		tx.commit();
	}

	public void update(Car t) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(t);
		tx.commit();
	}

	@Override
	public void delete(Car t) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.delete(t);
		tx.commit();
	}

	@Override
	public Car findByPK(long id) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Car car = (Car) session.get(Car.class, id);
		tx.commit();
		return car;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Car> getAll() {
		List<Car> lCars = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		lCars.addAll(session.createCriteria(Car.class).list());
		tx.commit();
		return lCars;
	}

}
