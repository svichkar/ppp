package com.nixsolutions.dao.hibernate;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nixsolutions.dao.WorkerDAO;
import com.nixsolutions.hibernate.entity.Worker;
import com.nixsolutions.hibernate.util.HibernateUtil;

public class WorkerDaoHibernate implements WorkerDAO {

	public static Logger LOG = LogManager.getLogger(WorkerDaoHibernate.class.getName());
	public static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public void createFrom(Worker entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(entity);
		tx.commit();
	}

	@Override
	public void update(Worker entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(entity);
		tx.commit();
	}

	@Override
	public void delete(Worker entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.delete(entity);
		tx.commit();
	}

	@Override
	public Worker getByPK(int id) {
		Session session = sessionFactory.getCurrentSession();
		Worker worker = null;
		Transaction tx = session.beginTransaction();
		worker = (Worker) session.get(Worker.class, id);
		tx.commit();
		return worker;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Worker> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<Worker> workerList = null;
		Transaction tx = session.beginTransaction();
		workerList = session.createCriteria(Worker.class).list();
		tx.commit();
		return workerList;
	}

}
