package com.nixsolutions.dao.hibernate;

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

public class OrderWorkerDaoHibernate implements OrderWorkerDAO {

	public static Logger LOG = LogManager.getLogger(OrderWorkerDaoHibernate.class.getName());
	public static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public void createFrom(OrderWorker entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(entity);
		tx.commit();
	}

	@Override
	public void update(OrderWorker entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(entity);
		tx.commit();
	}

	@Override
	public void delete(OrderWorker entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.delete(entity);
		tx.commit();
	}

	@Override
	public OrderWorker getByPK(int id) {
		Session session = sessionFactory.getCurrentSession();
		OrderWorker orderWorker = null;
		Transaction tx = session.beginTransaction();
		orderWorker = (OrderWorker) session.get(OrderWorker.class, id);
		tx.commit();
		return orderWorker;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderWorker> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<OrderWorker> orderWorkerList = null;
		Transaction tx = session.beginTransaction();
		orderWorkerList = session.createCriteria(OrderWorker.class).list();
		tx.commit();
		return orderWorkerList;
	}

	@Override
	public OrderWorker getByPK(long orderId, int workerId) {
		Session session = sessionFactory.getCurrentSession();
		OrderWorker orderWorker = null;
		Transaction tx = session.beginTransaction();
		orderWorker = (OrderWorker) session.createCriteria(OrderWorker.class)
				.add(Restrictions.eq("order.orderId", orderId))
				.add(Restrictions.eq("worker.workerId", workerId))
				.uniqueResult();
		tx.commit();
		return orderWorker;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderWorker> getByOrderId(long orderId) {
		Session session = sessionFactory.getCurrentSession();
		List<OrderWorker> orderWorkerList = null;
		Transaction tx = session.beginTransaction();
		orderWorkerList = session.createCriteria(OrderWorker.class)
				.add(Restrictions.eq("order.orderId", orderId))
				.list();
		tx.commit();
		return orderWorkerList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderWorker> getOrderWorkerByWorkerId(int workerId) {
		Session session = sessionFactory.getCurrentSession();
		List<OrderWorker> orderPartList = null;
		Transaction tx = session.beginTransaction();
		orderPartList = session.createCriteria(OrderWorker.class)
				.add(Restrictions.eq("worker.workerId", workerId))
				.list();
		tx.commit();
		return orderPartList;
	}

}
