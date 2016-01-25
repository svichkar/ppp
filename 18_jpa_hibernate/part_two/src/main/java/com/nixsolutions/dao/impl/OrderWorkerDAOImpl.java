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

public class OrderWorkerDAOImpl implements OrderWorkerDAO<OrderWorker> {

	private final static Logger LOG = LogManager.getLogger(OrderWorkerDAOImpl.class);
	public static SessionFactory sessionFactory;

	public OrderWorkerDAOImpl() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@Override
	public void create(OrderWorker t) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(t);
		tx.commit();
	}

	@Override
	public void update(OrderWorker t) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(t);
		tx.commit();
	}

	@Override
	public void delete(OrderWorker t) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.delete(t);
		tx.commit();
	}

	@Override
	public OrderWorker findByPK(long id) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderWorker> getAll() {
		List<OrderWorker> lOW = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		lOW.addAll(session.createCriteria(OrderWorker.class).list());
		tx.commit();
		return lOW;
	}

	@SuppressWarnings("unchecked")
	public List<OrderWorker> getAllForOrder(int order_id) {
		List<OrderWorker> lOW = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		OrderInWork oiw = (OrderInWork) session.createCriteria(OrderInWork.class).add(Restrictions.eq("order_id", order_id)).uniqueResult();
		lOW.addAll(session.createCriteria(OrderWorker.class).add(Restrictions.eq("order", oiw)).list());
		tx.commit();
		return lOW;
	}

	public OrderWorker findbyOrderAndWorker(long order_id, long worker_id) {
		OrderWorker ow = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		OrderInWork oiw = (OrderInWork) session.createCriteria(OrderInWork.class).add(Restrictions.eq("order_id", order_id)).uniqueResult();
		Worker w = (Worker) session.createCriteria(Worker.class).add(Restrictions.eq("worker_id", worker_id)).uniqueResult();
		
		ow = (OrderWorker) session.createCriteria(OrderWorker.class)
				.add(Restrictions.eq("order", oiw))
				.add(Restrictions.eq("worker", w)).uniqueResult();
		tx.commit();
		return ow;
	}
}
