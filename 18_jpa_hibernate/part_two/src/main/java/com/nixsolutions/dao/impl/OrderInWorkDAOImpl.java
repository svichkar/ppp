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
import com.nixsolutions.entities.Car;
import com.nixsolutions.entities.OrderInWork;
import com.nixsolutions.error.CustomDataException;

public class OrderInWorkDAOImpl implements OrderInWorkDAO<OrderInWork> {

	private final static Logger LOG = LogManager.getLogger(OrderInWorkDAOImpl.class);
	public static SessionFactory sessionFactory;

	public OrderInWorkDAOImpl() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@Override
	public void create(OrderInWork t) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(t);
			transaction.commit();
		} catch (Exception ex) {
			LOG.error(ex, ex);
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
			throw new CustomDataException(ex);
		}

	}

	@Override
	public void update(OrderInWork t) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(t);
			transaction.commit();
		} catch (Exception ex) {
			LOG.error(ex, ex);
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
			throw new CustomDataException(ex);
		}
	}

	@Override
	public void delete(OrderInWork t) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.delete(t);
			transaction.commit();
		} catch (Exception ex) {
			LOG.error(ex, ex);
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
			throw new CustomDataException(ex);
		}
	}

	@Override
	public OrderInWork findByPK(long id) {
		OrderInWork orderInWork = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			orderInWork = (OrderInWork) session.get(OrderInWork.class, id);
			tx.commit();
		} catch (Exception ex) {
			LOG.error(ex, ex);
			if (tx != null) {
				tx.rollback();
				session.close();
			}
			throw new CustomDataException(ex);
		}
		return orderInWork;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderInWork> getAll() {
		List<OrderInWork> orderInWorks = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			orderInWorks.addAll(session.createCriteria(OrderInWork.class).list());
			tx.commit();
		} catch (Exception ex) {
			LOG.error(ex, ex);
			if (tx != null) {
				tx.rollback();
				session.close();
			}
			throw new CustomDataException(ex);
		}
		return orderInWorks;
	}

}
