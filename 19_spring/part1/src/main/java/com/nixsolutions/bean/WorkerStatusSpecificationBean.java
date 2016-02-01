package com.nixsolutions.bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nixsolutions.app.HibernateUtil;
import com.nixsolutions.dao.GenericDao;
import com.nixsolutions.entities.Worker;
import com.nixsolutions.entities.WorkerStatusSpecification;

public class WorkerStatusSpecificationBean implements GenericDao<WorkerStatusSpecification> {

	private final static Logger LOG = LogManager.getLogger(WorkerStatusSpecificationBean.class);
	public static SessionFactory sessionFactory;

	public WorkerStatusSpecificationBean() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@Deprecated
	@Override
	public void create(WorkerStatusSpecification t) {

	}

	@Deprecated
	@Override
	public void update(WorkerStatusSpecification t) {

	}

	@Deprecated
	@Override
	public void delete(WorkerStatusSpecification t) {

	}

	@Override
	public WorkerStatusSpecification findByPK(long id) {
		WorkerStatusSpecification workerStatusSpec = null;
		Session session = null;
		Transaction transaction = null;
		Worker worker = null;

		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			worker = (Worker) session.createCriteria(Worker.class).add(Restrictions.eq("user_id", id)).uniqueResult();
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
		}

		if (worker != null) {
			workerStatusSpec = new WorkerStatusSpecification();
			workerStatusSpec.setF_name(worker.getF_name());
			workerStatusSpec.setL_name(worker.getL_name());
			workerStatusSpec.setId(worker.getWorkerId());
			workerStatusSpec.setSpec_id(worker.getSpec().getSpecId());
			workerStatusSpec.setSpec_name(worker.getSpec().getSpec_name());
			workerStatusSpec.setStatus_id(worker.getStatus().getStatusId());
			workerStatusSpec.setStatus_name(worker.getStatus().getStatus_name());
		}

		return workerStatusSpec;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<WorkerStatusSpecification> getAll() {
		List<WorkerStatusSpecification> results = new ArrayList<>();
		List<Worker> workers = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			workers.addAll(session.createCriteria(Worker.class).list());
			tx.commit();
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
				session.close();
			}
		}

		for (Worker worker : workers) {
			WorkerStatusSpecification wss = new WorkerStatusSpecification();
			wss.setF_name(worker.getF_name());
			wss.setL_name(worker.getL_name());
			wss.setId(worker.getWorkerId());
			wss.setSpec_id(worker.getSpec().getSpecId());
			wss.setSpec_name(worker.getSpec().getSpec_name());
			wss.setStatus_id(worker.getStatus().getStatusId());
			wss.setStatus_name(worker.getStatus().getStatus_name());
			if (!results.contains(wss)) {
				results.add(wss);
			}

		}
		return results;
	}

}
