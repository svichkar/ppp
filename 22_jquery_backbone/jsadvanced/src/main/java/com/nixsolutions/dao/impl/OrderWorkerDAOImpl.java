package com.nixsolutions.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.dao.OrderWorkerDAO;
import com.nixsolutions.hibernate.entity.OrderWorker;

@Repository("orderWorkerDAO")
@Transactional
public class OrderWorkerDAOImpl implements OrderWorkerDAO {

	public static Logger LOG = LogManager.getLogger(OrderWorkerDAOImpl.class.getName());
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void createFrom(OrderWorker entity) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate("orderWorker", entity);
		} catch (Exception ex) {
			LOG.error(ex);
		}
	}

	@Override
	public void update(OrderWorker entity) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate("orderWorker", entity);
		} catch (Exception ex) {
			LOG.error(ex);
		}
	}

	@Override
	public void delete(OrderWorker entity) {
		try {
			sessionFactory.getCurrentSession().delete("orderWorker", entity);
		} catch (Exception ex) {
			LOG.error(ex);
		}
	}

	@Override
	public OrderWorker getByPK(int id) {
		try {
			return (OrderWorker) sessionFactory.getCurrentSession().byId(OrderWorker.class).load(id);
		} catch (Exception ex) {
			LOG.error(ex);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderWorker> getAll() {
		try {
			return sessionFactory.getCurrentSession().createCriteria(OrderWorker.class).list();
		} catch (Exception ex) {
			LOG.error(ex);
			return null;
		}
	}

	@Override
	public OrderWorker getByPK(long orderId, int workerId) {
		try {
			return (OrderWorker) sessionFactory.getCurrentSession().createCriteria(OrderWorker.class)
					.add(Restrictions.eq("order.orderId", orderId))
					.add(Restrictions.eq("worker.workerId", workerId))
					.uniqueResult();
		} catch (Exception ex) {
			LOG.error(ex);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderWorker> getByOrderId(long orderId) {
		try {
			return sessionFactory.getCurrentSession().createCriteria(OrderWorker.class)
					.add(Restrictions.eq("order.orderId", orderId))
					.list();
		} catch (Exception ex) {
			LOG.error(ex);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderWorker> getOrderWorkerByWorkerId(int workerId) {
		try {
			return sessionFactory.getCurrentSession().createCriteria(OrderWorker.class)
					.add(Restrictions.eq("worker.workerId", workerId))
					.list();
		} catch (Exception ex) {
			LOG.error(ex);
			return null;
		}
	}

}
