package com.nixsolutions.dao.hibernate;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nixsolutions.dao.CarDAO;
import com.nixsolutions.hibernate.entity.Car;
import com.nixsolutions.hibernate.util.HibernateUtil;

public class CarDaoHibernate implements CarDAO {

	public static Logger LOG = LogManager.getLogger(CarDaoHibernate.class.getName());
	public static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public void createFrom(Car entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(entity);
		tx.commit();
	}

	@Override
	public void update(Car entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(entity);
		tx.commit();
	}

	@Override
	public void delete(Car entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.delete(entity);
		tx.commit();
	}

	@Override
	public Car getByPK(int id) {
		Session session = sessionFactory.getCurrentSession();
		Car car = null;
		Transaction tx = session.beginTransaction();
		car = (Car) session.get(Car.class, id);
		tx.commit();
		return car;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Car> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<Car> carList = null;
		Transaction tx = session.beginTransaction();
		carList = session.createCriteria(Car.class).list();
		tx.commit();
		return carList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Car> getCarsByCustomerId(int customerId) {
		Session session = sessionFactory.getCurrentSession();
		List<Car> carList = null;
		Transaction tx = session.beginTransaction();
		carList = session.createCriteria(Car.class).add(Restrictions.eq("customer.customerId", customerId)).list();
		tx.commit();
		return carList;
	}
}
