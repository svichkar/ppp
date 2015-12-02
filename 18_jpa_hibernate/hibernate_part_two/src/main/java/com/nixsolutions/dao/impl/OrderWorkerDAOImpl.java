package com.nixsolutions.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nixsolutions.dao.OrderWorkerDAO;
import com.nixsolutions.hibernate.entity.OrderWorker;
import com.nixsolutions.hibernate.util.HibernateUtil;

public class OrderWorkerDAOImpl implements OrderWorkerDAO {

	public static Logger LOG = LogManager.getLogger(OrderWorkerDAOImpl.class.getName());
	public static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public OrderWorker createFrom(OrderWorker entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		OrderWorker orderWorker = null;
		try {
			session.saveOrUpdate("orderWorker", entity);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		session = sessionFactory.getCurrentSession();
		tx = session.beginTransaction();
		try {
			orderWorker = (OrderWorker) session.get(OrderWorker.class, entity.getId());
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return orderWorker;
	}

	@Override
	public void update(OrderWorker entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate("orderWorker", entity);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
	}

	@Override
	public void delete(OrderWorker entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete("orderWorker", entity);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
	}

	@Override
	public OrderWorker getByPK(int id) {
		Session session = sessionFactory.getCurrentSession();
		OrderWorker orderWorker = null;
		Transaction tx = session.beginTransaction();
		try {
			orderWorker = (OrderWorker) session.byId(OrderWorker.class).load(id);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return orderWorker;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderWorker> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<OrderWorker> orderWorkerList = null;
		Transaction tx = session.beginTransaction();
		try {
			orderWorkerList = session.createCriteria(OrderWorker.class).list();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return orderWorkerList;
	}

	@Override
	public OrderWorker getByPK(long orderId, int workerId) {
		Session session = sessionFactory.getCurrentSession();
		OrderWorker orderWorker = null;
		Transaction tx = session.beginTransaction();
		try {
			orderWorker = (OrderWorker) session.createCriteria(OrderWorker.class)
					.add(Restrictions.eq("order.orderId", orderId)).add(Restrictions.eq("worker.workerId", workerId)).uniqueResult();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return orderWorker;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderWorker> getByOrderId(long orderId) {
		Session session = sessionFactory.getCurrentSession();
		List<OrderWorker> orderWorkerList = null;
		Transaction tx = session.beginTransaction();
		try {
			orderWorkerList = session.createCriteria(OrderWorker.class).add(Restrictions.eq("order.orderId", orderId)).list();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return orderWorkerList;
	}

}
