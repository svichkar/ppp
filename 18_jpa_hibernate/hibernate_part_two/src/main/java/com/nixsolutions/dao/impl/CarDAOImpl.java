package com.nixsolutions.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nixsolutions.hibernate.entity.Car;
import com.nixsolutions.hibernate.entity.Customer;
import com.nixsolutions.hibernate.util.HibernateUtil;
import com.nixsolutions.dao.CarDAO;

public class CarDAOImpl implements CarDAO {

	public static Logger LOG = LogManager.getLogger(CarDAOImpl.class.getName());
	public static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public void createFrom(Car entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		//Car car = null;
		try {
			session.saveOrUpdate("car", entity);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
/*		session = sessionFactory.getCurrentSession();
		tx = session.beginTransaction();
		try {
			car = (Car) session.get(Car.class, entity.getCarId());
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return car;*/
	}

	@Override
	public void update(Car entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate("car", entity);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
	}

	@Override
	public void delete(Car entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete("car", entity);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
	}

	@Override
	public Car getByPK(int id) {
		Session session = sessionFactory.getCurrentSession();
		Car car = null;
		Transaction tx = session.beginTransaction();
		try {
			car = (Car) session.byId(Car.class).load(id);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return car;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Car> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<Car> carList = null;
		Transaction tx = session.beginTransaction();
		try {
			carList = session.createCriteria(Car.class).list();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return carList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Car> getCarsByCustomer(Customer customer) {
		Session session = sessionFactory.getCurrentSession();
		List<Car> carList = null;
		Transaction tx = session.beginTransaction();
		try {
			carList = session.createCriteria(Car.class).add(Restrictions.eq("customer.customerId", customer.getCustomerId()))
					.list();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return carList;
	}
}
