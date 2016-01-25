package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nixsolutions.app.HibernateUtil;
import com.nixsolutions.dao.OrderStatusDAO;
import com.nixsolutions.entities.OrderStatus;

public class OrderStatusDAOImpl implements OrderStatusDAO<OrderStatus> {

	private final static Logger LOG = LogManager.getLogger(OrderStatusDAOImpl.class);
	public static SessionFactory sessionFactory;

	public OrderStatusDAOImpl() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@Override
	public void create(OrderStatus t) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(t);
		tx.commit();
	}

	@Override
	public void update(OrderStatus t) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(t);
		tx.commit();
	}

	@Override
	public void delete(OrderStatus t) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.delete(t);
		tx.commit();
	}

	@Override
	public OrderStatus findByPK(long id) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		OrderStatus os = (OrderStatus) session.get(OrderStatus.class, id);
		tx.commit();
		return os;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<OrderStatus> getAll() {
		List<OrderStatus> lOS = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		lOS.addAll(session.createCriteria(OrderStatus.class).list());
		tx.commit();
		return lOS;
	}

}
