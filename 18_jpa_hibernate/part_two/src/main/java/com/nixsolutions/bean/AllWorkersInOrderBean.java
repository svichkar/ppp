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
		List<AllWorkersInOrder> listResults = new ArrayList<>();

		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		List<OrderWorker> lOW = session.createCriteria(OrderWorker.class).list();
		tx.commit();

		for (OrderWorker orderWorker : lOW) {
			AllWorkersInOrder oneWO = new AllWorkersInOrder();
			oneWO.setCompleted(orderWorker.getCompleted());
			oneWO.setF_name(orderWorker.getWorker().getF_name());
			oneWO.setL_name(orderWorker.getWorker().getL_name());
			oneWO.setId(orderWorker.getOrderInWork().getOrderInWorkId());
			oneWO.setWorker_id(orderWorker.getWorker().getWorkerId());
			listResults.add(oneWO);
		}

		return listResults;
	}

	@SuppressWarnings("unchecked")
	public List<AllWorkersInOrder> getAll(long order_id) {
		List<AllWorkersInOrder> listResults = new ArrayList<>();

		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		OrderInWork oiw = (OrderInWork) session.createCriteria(OrderInWork.class).add(Restrictions.eq("order_id", order_id)).uniqueResult();
		List<OrderWorker> lOW = session.createCriteria(OrderWorker.class).add(Restrictions.eq("order", oiw))
				.list();
		tx.commit();

		for (OrderWorker orderWorker : lOW) {
			AllWorkersInOrder oneWO = new AllWorkersInOrder();
			oneWO.setCompleted(orderWorker.getCompleted());
			oneWO.setF_name(orderWorker.getWorker().getF_name());
			oneWO.setL_name(orderWorker.getWorker().getL_name());
			oneWO.setId(orderWorker.getOrderInWork().getOrderInWorkId());
			oneWO.setWorker_id(orderWorker.getWorker().getWorkerId());
			listResults.add(oneWO);
		}

		return listResults;
	}

	public AllWorkersInOrder findByOrderAndWorker(long order_id, long worker_id) {

		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		OrderInWork oiw = (OrderInWork) session.createCriteria(OrderInWork.class).add(Restrictions.eq("order_id", order_id)).uniqueResult();
		Worker w = (Worker) session.createCriteria(Worker.class).add(Restrictions.eq("worker_id", worker_id)).uniqueResult();
		OrderWorker ow = (OrderWorker) session.createCriteria(OrderWorker.class)
				.add(Restrictions.eq("order", oiw)).add(Restrictions.eq("worker", w)).uniqueResult();
		tx.commit();

		AllWorkersInOrder oneWO = new AllWorkersInOrder();
		oneWO.setCompleted(ow.getCompleted());
		oneWO.setF_name(ow.getWorker().getF_name());
		oneWO.setL_name(ow.getWorker().getL_name());
		oneWO.setId(ow.getOrderInWork().getOrderInWorkId());
		oneWO.setWorker_id(ow.getWorker().getWorkerId());

		return oneWO;
	}

}
