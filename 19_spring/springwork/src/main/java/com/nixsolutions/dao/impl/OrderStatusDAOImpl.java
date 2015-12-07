package com.nixsolutions.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nixsolutions.dao.OrderStatusDAO;
import com.nixsolutions.hibernate.entity.OrderStatus;
import com.nixsolutions.hibernate.util.HibernateUtil;

public class OrderStatusDAOImpl implements OrderStatusDAO {

	public static Logger LOG = LogManager.getLogger(OrderStatusDAOImpl.class.getName());
	public static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public void createFrom(OrderStatus entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate("orderStatus", entity);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
	}

	@Override
	public void update(OrderStatus entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate("orderStatus", entity);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
	}

	@Override
	public void delete(OrderStatus entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete("orderStatus", entity);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
	}

	@Override
	public OrderStatus getByPK(int id) {
		Session session = sessionFactory.getCurrentSession();
		OrderStatus orderStatus = null;
		Transaction tx = session.beginTransaction();
		try {
			orderStatus = (OrderStatus) session.byId(OrderStatus.class).load(id);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return orderStatus;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderStatus> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<OrderStatus> orderStatusList = null;
		Transaction tx = session.beginTransaction();
		try {
			orderStatusList = session.createCriteria(OrderStatus.class).list();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return orderStatusList;
	}
}
