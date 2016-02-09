package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.dao.OrderWorkerDAO;
import com.nixsolutions.entities.OrderInWork;
import com.nixsolutions.entities.OrderWorker;
import com.nixsolutions.entities.Worker;

@Repository
@Transactional
public class OrderWorkerDAOImpl implements OrderWorkerDAO {

	private final static Logger LOG = LogManager.getLogger(OrderWorkerDAOImpl.class);
	@Autowired
	protected SessionFactory sessionFactory;


	@Override
	public void create(OrderWorker t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public void update(OrderWorker t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public void delete(OrderWorker t) {
		sessionFactory.getCurrentSession().delete(t);
	}

	@Deprecated
	@Override
	public OrderWorker findByPK(long id) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderWorker> getAll() {
		List<OrderWorker> orderWorkers = new ArrayList<>();
		orderWorkers.addAll(sessionFactory.getCurrentSession().createCriteria(OrderWorker.class).list());
		return orderWorkers;
	}

	@SuppressWarnings("unchecked")
	public List<OrderWorker> getAllForOrder(long orderid) {
		List<OrderWorker> orderWorkers = new ArrayList<>();

		OrderInWork orderInWork = (OrderInWork) sessionFactory.getCurrentSession().createCriteria(OrderInWork.class)
				.add(Restrictions.eq("order_id", orderid)).uniqueResult();
		orderWorkers.addAll(sessionFactory.getCurrentSession().createCriteria(OrderWorker.class)
				.add(Restrictions.eq("order", orderInWork)).list());
		return orderWorkers;
	}

	@Override
	public OrderWorker findbyOrderAndWorker(long orderid, long workerid) {
			OrderInWork orderInWork = (OrderInWork) sessionFactory.getCurrentSession().createCriteria(OrderInWork.class)
					.add(Restrictions.eq("order_id", orderid)).uniqueResult();
			Worker worker = (Worker) sessionFactory.getCurrentSession().createCriteria(Worker.class)
					.add(Restrictions.eq("worker_id", workerid)).uniqueResult();
	
		return (OrderWorker) sessionFactory.getCurrentSession().createCriteria(OrderWorker.class)
				.add(Restrictions.eq("order", orderInWork)).add(Restrictions.eq("worker", worker)).uniqueResult();
	}
}
