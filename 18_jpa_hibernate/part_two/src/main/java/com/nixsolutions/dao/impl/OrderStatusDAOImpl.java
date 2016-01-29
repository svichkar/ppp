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
import com.nixsolutions.error.CustomDataException;

public class OrderStatusDAOImpl implements OrderStatusDAO<OrderStatus> {

	private final static Logger LOG = LogManager.getLogger(OrderStatusDAOImpl.class);
	public static SessionFactory sessionFactory;

	public OrderStatusDAOImpl() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@Override
	public void create(OrderStatus t) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(t);
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
			throw new CustomDataException(ex);
		}
	}

	@Override
	public void update(OrderStatus t) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(t);
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
			throw new CustomDataException(ex);
		}
	}

	@Override
	public void delete(OrderStatus t) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.delete(t);
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
			throw new CustomDataException(ex);
		}
	}

	@Override
	public OrderStatus findByPK(long id) {
		Session session = null;
		Transaction transaction = null;
		OrderStatus orderStatus = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			orderStatus = (OrderStatus) session.get(OrderStatus.class, id);
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
			throw new CustomDataException(ex);
		}
		return orderStatus;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderStatus> getAll() {
		List<OrderStatus> ordersStatuses = new ArrayList<>();
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			ordersStatuses.addAll(session.createCriteria(OrderStatus.class).list());
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
			throw new CustomDataException(ex);
		}
		return ordersStatuses;
	}

}
