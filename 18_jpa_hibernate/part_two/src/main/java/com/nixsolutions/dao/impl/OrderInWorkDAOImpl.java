package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nixsolutions.app.HibernateUtil;
import com.nixsolutions.dao.OrderInWorkDAO;
import com.nixsolutions.entities.OrderInWork;

public class OrderInWorkDAOImpl implements OrderInWorkDAO<OrderInWork> {

	private final static Logger LOG = LogManager.getLogger(OrderInWorkDAOImpl.class);
	public static SessionFactory sessionFactory;

	public OrderInWorkDAOImpl() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@Override
	public void create(OrderInWork t) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(t);
		tx.commit();
	}

	@Override
	public void update(OrderInWork t) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(t);
		tx.commit();
	}

	@Override
	public void delete(OrderInWork t) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.delete(t);
		tx.commit();
	}

	@Override
	public OrderInWork findByPK(long id) {
		OrderInWork oiw = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		oiw = (OrderInWork) session.get(OrderInWork.class, id);
		tx.commit();
		return oiw;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<OrderInWork> getAll() {
		List<OrderInWork> lOIW = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		lOIW.addAll(session.createCriteria(OrderInWork.class).list());
		tx.commit();
		return lOIW;
	}

}
