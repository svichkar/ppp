package com.nixsolutions.dao.hibernate;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nixsolutions.dao.OrderInWorkDAO;
import com.nixsolutions.hibernate.entity.OrderInWork;
import com.nixsolutions.hibernate.util.HibernateUtil;

public class OrderInWorkDaoHibernate implements OrderInWorkDAO {

	public static Logger LOG = LogManager.getLogger(OrderInWorkDaoHibernate.class.getName());
	public static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public void createFrom(OrderInWork entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(entity);
		tx.commit();
	}

	@Override
	public void update(OrderInWork entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(entity);
		tx.commit();
	}

	@Override
	public void delete(OrderInWork entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.delete(entity);
		tx.commit();
	}

	@Override
	public OrderInWork getByPK(long id) {
		Session session = sessionFactory.getCurrentSession();
		OrderInWork orderInWork = null;
		Transaction tx = session.beginTransaction();
		orderInWork = (OrderInWork) session.get(OrderInWork.class, id);
		tx.commit();
		return orderInWork;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderInWork> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<OrderInWork> orderInWorkList = null;
		Transaction tx = session.beginTransaction();
		orderInWorkList = session.createCriteria(OrderInWork.class).list();
		tx.commit();
		return orderInWorkList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderInWork> getOrdersByUserId(int userId) {
		Session session = sessionFactory.getCurrentSession();
		List<OrderInWork> orderInWorkList = null;
		Transaction tx = session.beginTransaction();
		orderInWorkList = session.createCriteria(OrderInWork.class, "order")
				.createAlias("order.car", "car")
				.createAlias("car.customer", "customer")
				.createAlias("customer.user", "user")
				.add(Restrictions.eq("user.userId", userId))
				.list();
		tx.commit();
		return orderInWorkList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderInWork> getOrdersByCarId(int carId) {
		Session session = sessionFactory.getCurrentSession();
		List<OrderInWork> orderInWorkList = null;
		Transaction tx = session.beginTransaction();
		orderInWorkList = session.createCriteria(OrderInWork.class).add(Restrictions.eq("car.carId", carId)).list();
		tx.commit();
		return orderInWorkList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderInWork> getOrdersByCustomerId(int customerId) {
		Session session = sessionFactory.getCurrentSession();
		List<OrderInWork> orderInWorkList = null;
		Transaction tx = session.beginTransaction();
		orderInWorkList = session.createCriteria(OrderInWork.class, "order")
				.createAlias("order.car", "car")
				.createAlias("car.customer", "customer")
				.add(Restrictions.eq("customer.customerId", customerId))
				.list();
		tx.commit();
		return orderInWorkList;
	}

	@Override
	public OrderInWork getByPK(int id) {
		return getByPK((long) id);
	}

}
