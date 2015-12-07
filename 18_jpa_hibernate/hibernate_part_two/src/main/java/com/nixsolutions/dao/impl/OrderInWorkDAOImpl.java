package com.nixsolutions.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nixsolutions.dao.OrderInWorkDAO;
import com.nixsolutions.hibernate.entity.Car;
import com.nixsolutions.hibernate.entity.Customer;
import com.nixsolutions.hibernate.entity.OrderInWork;
import com.nixsolutions.hibernate.entity.User;
import com.nixsolutions.hibernate.util.HibernateUtil;

public class OrderInWorkDAOImpl implements OrderInWorkDAO {

	public static Logger LOG = LogManager.getLogger(OrderInWorkDAOImpl.class.getName());
	public static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public void createFrom(OrderInWork entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate("orderInWork", entity);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
/*		session = sessionFactory.getCurrentSession();
		tx = session.beginTransaction();
		try {
			orderInWork = (OrderInWork) session.get(OrderInWork.class, entity.getOrderId());
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return orderInWork;*/
	}

	@Override
	public void update(OrderInWork entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate("orderInWork", entity);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
	}

	@Override
	public void delete(OrderInWork entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete("orderInWork", entity);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
	}

	@Override
	public OrderInWork getByPK(long id) {
		Session session = sessionFactory.getCurrentSession();
		OrderInWork orderInWork = null;
		Transaction tx = session.beginTransaction();
		try {
			orderInWork = (OrderInWork) session.byId(OrderInWork.class).load(id);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return orderInWork;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderInWork> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<OrderInWork> orderInWorkList = null;
		Transaction tx = session.beginTransaction();
		try {
			orderInWorkList = session.createCriteria(OrderInWork.class).list();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return orderInWorkList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderInWork> getOrdersByUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		List<OrderInWork> orderInWorkList = null;
		Transaction tx = session.beginTransaction();
		try {
		orderInWorkList = session.createCriteria(OrderInWork.class, "order").createAlias("order.car", "car")
				.createAlias("car.customer", "customer").createAlias("customer.user", "user")
				.add(Restrictions.eq("user.userId", user.getUserId())).list();
		tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return orderInWorkList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderInWork> getOrdersByCar(Car car) {
		Session session = sessionFactory.getCurrentSession();
		List<OrderInWork> orderInWorkList = null;
		Transaction tx = session.beginTransaction();
		try {
			orderInWorkList = session.createCriteria(OrderInWork.class).add(Restrictions.eq("car.carId", car.getCarId()))
					.list();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return orderInWorkList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderInWork> getOrdersByCustomer(Customer customer) {
		Session session = sessionFactory.getCurrentSession();
		List<OrderInWork> orderInWorkList = null;
		Transaction tx = session.beginTransaction();
		try {
			orderInWorkList = session.createCriteria(OrderInWork.class, "order").createAlias("order.car", "car")
					.createAlias("car.customer", "customer")
					.add(Restrictions.eq("customer.customerId", customer.getCustomerId()))
					.list();
			tx.commit();
		} catch (Exception ex) {
		tx.rollback();
		LOG.error(ex);
		}
		return orderInWorkList;
	}

	@Override
	public OrderInWork getByPK(int id) {
		return getByPK((long) id);
	}

}
