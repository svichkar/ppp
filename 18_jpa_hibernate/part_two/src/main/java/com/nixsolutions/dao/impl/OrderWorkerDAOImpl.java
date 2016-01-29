package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nixsolutions.app.HibernateUtil;
import com.nixsolutions.dao.OrderWorkerDAO;
import com.nixsolutions.entities.OrderInWork;
import com.nixsolutions.entities.OrderWorker;
import com.nixsolutions.entities.Worker;
import com.nixsolutions.error.CustomDataException;

public class OrderWorkerDAOImpl implements OrderWorkerDAO<OrderWorker> {

	private final static Logger LOG = LogManager.getLogger(OrderWorkerDAOImpl.class);
	public static SessionFactory sessionFactory;

	public OrderWorkerDAOImpl() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@Override
	public void create(OrderWorker t) {
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
	public void update(OrderWorker t) {
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
	public void delete(OrderWorker t) {
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
	public OrderWorker findByPK(long id) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderWorker> getAll() {
		List<OrderWorker> orderWorkers = new ArrayList<>();
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			orderWorkers.addAll(session.createCriteria(OrderWorker.class).list());
			transaction.commit();
		} catch (Exception ex) {
			LOG.error(ex, ex);
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
			throw new CustomDataException(ex);
		}
		return orderWorkers;
	}

	@SuppressWarnings("unchecked")
	public List<OrderWorker> getAllForOrder(long orderid) {
		List<OrderWorker> orderWorkers = new ArrayList<>();
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			OrderInWork orderInWork = (OrderInWork) session.createCriteria(OrderInWork.class)
					.add(Restrictions.eq("order_id", orderid)).uniqueResult();
			orderWorkers.addAll(
					session.createCriteria(OrderWorker.class).add(Restrictions.eq("order", orderInWork)).list());
			transaction.commit();
		} catch (Exception ex) {
			LOG.error(ex, ex);
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
			throw new CustomDataException(ex);
		}
		return orderWorkers;
	}

	public OrderWorker findbyOrderAndWorker(long orderid, long workerid) {
		OrderWorker orderWorker = null;
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			OrderInWork orderInWork = (OrderInWork) session.createCriteria(OrderInWork.class)
					.add(Restrictions.eq("order_id", orderid)).uniqueResult();
			Worker worker = (Worker) session.createCriteria(Worker.class).add(Restrictions.eq("worker_id", workerid))
					.uniqueResult();
			orderWorker = (OrderWorker) session.createCriteria(OrderWorker.class)
					.add(Restrictions.eq("order", orderInWork)).add(Restrictions.eq("worker", worker)).uniqueResult();
			transaction.commit();
		} catch (Exception ex) {
			LOG.error(ex, ex);
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
			throw new CustomDataException(ex);
		}
		return orderWorker;
	}
}
