package com.nixsolutions.bean;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.ietf.jgss.Oid;

import com.nixsolutions.app.HibernateUtil;
import com.nixsolutions.dao.AllWorkersInOrderDAO;
import com.nixsolutions.entities.AllWorkersInOrder;
import com.nixsolutions.entities.OrderInWork;
import com.nixsolutions.entities.OrderWorker;
import com.nixsolutions.entities.Worker;

public class AllWorkersInOrderBean implements AllWorkersInOrderDAO<AllWorkersInOrder> {

	private final static Logger LOG = LogManager.getLogger(AllWorkersInOrderBean.class);
	public static SessionFactory sessionFactory;

	public AllWorkersInOrderBean() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@Deprecated
	@Override
	public void create(AllWorkersInOrder t) {

	}

	@Deprecated
	@Override
	public void update(AllWorkersInOrder t) {

	}

	@Deprecated
	@Override
	public void delete(AllWorkersInOrder t) {

	}

	@Deprecated
	@Override
	public AllWorkersInOrder findByPK(long id) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AllWorkersInOrder> getAll() {
		List<AllWorkersInOrder> results = new ArrayList<>();
		List<OrderWorker> orderWorkers = new ArrayList<>();
		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			orderWorkers.addAll(session.createCriteria(OrderWorker.class).list());
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
		}

		for (OrderWorker orderWorker : orderWorkers) {
			AllWorkersInOrder allWorkerInOrder = new AllWorkersInOrder();
			allWorkerInOrder.setCompleted(orderWorker.getCompleted());
			allWorkerInOrder.setF_name(orderWorker.getWorker().getF_name());
			allWorkerInOrder.setL_name(orderWorker.getWorker().getL_name());
			allWorkerInOrder.setId(orderWorker.getOrderInWork().getOrderInWorkId());
			allWorkerInOrder.setWorker_id(orderWorker.getWorker().getWorkerId());
			results.add(allWorkerInOrder);
		}

		return results;
	}

	@SuppressWarnings("unchecked")
	public List<AllWorkersInOrder> getAll(long orderid) {
		List<AllWorkersInOrder> results = new ArrayList<>();
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
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
		}

		for (OrderWorker orderWorker : orderWorkers) {
			AllWorkersInOrder allWorkerInOrder = new AllWorkersInOrder();
			allWorkerInOrder.setCompleted(orderWorker.getCompleted());
			allWorkerInOrder.setF_name(orderWorker.getWorker().getF_name());
			allWorkerInOrder.setL_name(orderWorker.getWorker().getL_name());
			allWorkerInOrder.setId(orderWorker.getOrderInWork().getOrderInWorkId());
			allWorkerInOrder.setWorker_id(orderWorker.getWorker().getWorkerId());
			results.add(allWorkerInOrder);
		}

		return results;
	}

	public AllWorkersInOrder findByOrderAndWorker(long orderid, long worderid) {
		Session session = null;
		Transaction transaction = null;
		OrderWorker orderWorker = null;
		AllWorkersInOrder allWorkerInOrder = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			OrderInWork orderInWork = (OrderInWork) session.createCriteria(OrderInWork.class)
					.add(Restrictions.eq("order_id", orderid)).uniqueResult();
			Worker worker = (Worker) session.createCriteria(Worker.class).add(Restrictions.eq("worker_id", worderid))
					.uniqueResult();
			orderWorker = (OrderWorker) session.createCriteria(OrderWorker.class)
					.add(Restrictions.eq("order", orderInWork)).add(Restrictions.eq("worker", worker)).uniqueResult();
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
		}

		if (orderWorker != null) {
			allWorkerInOrder = new AllWorkersInOrder();
			allWorkerInOrder.setCompleted(orderWorker.getCompleted());
			allWorkerInOrder.setF_name(orderWorker.getWorker().getF_name());
			allWorkerInOrder.setL_name(orderWorker.getWorker().getL_name());
			allWorkerInOrder.setId(orderWorker.getOrderInWork().getOrderInWorkId());
			allWorkerInOrder.setWorker_id(orderWorker.getWorker().getWorkerId());
		}

		return allWorkerInOrder;
	}

}
