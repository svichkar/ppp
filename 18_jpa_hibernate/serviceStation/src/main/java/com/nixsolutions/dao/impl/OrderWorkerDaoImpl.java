/**
 * 
 */
package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;
//import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.OrderWorkerDao;
import com.nixsolutions.entity.OrderInWork;
import com.nixsolutions.entity.OrderWorker;
import com.nixsolutions.entity.Worker;
import com.nixsolutions.util.HibernateUtil;

/**
 * @author mixeyes
 *
 */
public class OrderWorkerDaoImpl implements OrderWorkerDao {

	private final static Logger logger = LogManager.getLogger();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.Order_workerDao#getAll()
	 */
	@Override
	public List<OrderWorker> getAll() {
		List<OrderWorker> order_workers = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			order_workers = session.createCriteria(OrderWorker.class).list();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return order_workers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.Order_workerDao#
	 * assignWorkerToOrder(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void assignWorkerToOrder(Integer order_id, Integer worker_id) {
		OrderInWork orderInWork = DaoFactory.getOrderInWorkDaoImpl().getOrderByID(order_id);
		Worker worker = DaoFactory.getWorkerDaoImpl().getWorkerByID(worker_id);
		OrderWorker orderWorker = new OrderWorker();
		orderWorker.setOrder(orderInWork);
		orderWorker.setWorker(worker);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate(orderWorker);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		}finally {
			if(session.isOpen())
				session.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.Order_workerDao#changeStatus(
	 * java.lang.Integer, java.lang.Integer, boolean)
	 */
	@Override
	public void changeStatus(Integer order_id, Integer worker_id, boolean isCompleted) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		OrderWorker orderWorker = null;
		Transaction tx = session.beginTransaction();
		try {
			orderWorker = (OrderWorker) session.createCriteria(OrderWorker.class)
					.add(Restrictions.eq("order.order_id", order_id))
					.add(Restrictions.eq("worker.worker_id", worker_id)).uniqueResult();
			tx.commit();
			orderWorker.setCompleted(isCompleted);
			session.saveOrUpdate(orderWorker);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		} finally {
			if (session.isOpen())
				session.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.Order_workerDao#
	 * assignWorkerToOrder(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public OrderInWork getActiveOrderByWorkerID(Integer worker_id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		OrderWorker orderWorker = null;
		Transaction tx = session.beginTransaction();
		try {
			orderWorker = (OrderWorker) session.createCriteria(OrderWorker.class)
					.add(Restrictions.eq("worker.worker_id", worker_id)).add(Restrictions.eq("isCompleted", false))
					.uniqueResult();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return orderWorker.getOrder();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.Order_workerDao#
	 * assignWorkerToOrder(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<OrderWorker> getWorkersByOrderID(Integer order_id) {
		List<OrderWorker> order_workers = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			order_workers = session.createCriteria(OrderWorker.class, "orWork").createAlias("orWork.order", "order")
					.add(Restrictions.eq("order.orderId", order_id)).list();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return order_workers;
	}
}
