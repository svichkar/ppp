package com.nixsolutions.dao.hibernate;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nixsolutions.dao.OrderStatusDAO;
import com.nixsolutions.hibernate.entity.OrderStatus;
import com.nixsolutions.hibernate.util.HibernateUtil;

public class OrderStatusDaoHibernate implements OrderStatusDAO {

	public static Logger LOG = LogManager.getLogger(OrderStatusDaoHibernate.class.getName());
	public static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public void createFrom(OrderStatus entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(entity);
		tx.commit();
	}

	@Override
	public void update(OrderStatus entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(entity);
		tx.commit();
	}

	@Override
	public void delete(OrderStatus entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.delete(entity);
		tx.commit();
	}

	@Override
	public OrderStatus getByPK(int id) {
		Session session = sessionFactory.getCurrentSession();
		OrderStatus orderStatus = null;
		Transaction tx = session.beginTransaction();
		orderStatus = (OrderStatus) session.get(OrderStatus.class, id);
		tx.commit();
		return orderStatus;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderStatus> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<OrderStatus> orderStatusList = null;
		Transaction tx = session.beginTransaction();
		orderStatusList = session.createCriteria(OrderStatus.class).list();
		tx.commit();
		return orderStatusList;
	}
}
