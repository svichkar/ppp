/**
 * 
 */
package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;
//import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nixsolutions.dao.OrderWorkerDao;
import com.nixsolutions.entity.OrderInWork;
import com.nixsolutions.entity.OrderWorker;

/**
 * @author mixeyes
 *
 */
@Repository("orderWorkerDao")
@Transactional
public class OrderWorkerDaoImpl implements OrderWorkerDao {

	private final static Logger logger = LogManager.getLogger();
	@Autowired
	SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.Order_workerDao#getAll()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderWorker> getAll() {
		List<OrderWorker> order_workers = new ArrayList<>();
		try {
			order_workers = sessionFactory.getCurrentSession().createCriteria(OrderWorker.class).list();
		} catch (Exception ex) {
			logger.error(ex);
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
	public void saveOrUpdateOrderWorker(OrderWorker orderWorker) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(orderWorker);
		} catch (Exception ex) {
			logger.error(ex);
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
	public OrderWorker getOrderWorker(Integer order_id, Integer worker_id){
		try {
			return (OrderWorker) sessionFactory.getCurrentSession().createCriteria(OrderWorker.class, "oWorker")
					.createAlias("oWorker.order", "order")
					.createAlias("oWorker.worker", "worker")
					.add(Restrictions.eq("order.orderId", order_id))
					.add(Restrictions.eq("worker.workerId", worker_id)).uniqueResult();
		} catch (Exception ex) {
			logger.error(ex);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.Order_workerDao#
	 * assignWorkerToOrder(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public OrderInWork getActiveOrderByWorkerID(Integer worker_id) {
		OrderWorker orderWorker = null;
		try {
			orderWorker = (OrderWorker) sessionFactory.getCurrentSession().createCriteria(OrderWorker.class)
					.add(Restrictions.eq("worker.worker_id", worker_id)).add(Restrictions.eq("isCompleted", false))
					.uniqueResult();
		} catch (Exception ex) {
			logger.error(ex);
		}
		return orderWorker.getOrder();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.Order_workerDao#
	 * assignWorkerToOrder(java.lang.Integer, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderWorker> getWorkersByOrderID(Integer order_id) {
		List<OrderWorker> order_workers = null;
		try {
			order_workers = sessionFactory.getCurrentSession().createCriteria(OrderWorker.class, "orWork")
					.createAlias("orWork.order", "order").add(Restrictions.eq("order.orderId", order_id)).list();
		} catch (Exception ex) {
			logger.error(ex);
		}
		return order_workers;
	}
}
