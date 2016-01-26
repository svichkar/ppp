package com.nixsolutions.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.DistinctRootEntityResultTransformer;

import com.nixsolutions.app.HibernateUtil;
import com.nixsolutions.dao.WorkerDAO;
import com.nixsolutions.entities.OrderInWork;
import com.nixsolutions.entities.Worker;

public class WorkerDAOImpl implements WorkerDAO<Worker> {

	private final static Logger LOG = LogManager.getLogger(WorkerDAOImpl.class);
	public static SessionFactory sessionFactory;

	public WorkerDAOImpl() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@Override
	public void create(Worker t) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(t);
		tx.commit();
	}

	@Override
	public void update(Worker t) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(t);
		tx.commit();
	}

	@Override
	public void delete(Worker t) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.delete(t);
		tx.commit();
	}

	@Override
	public Worker findByPK(long id) {
		Worker worker = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		worker = (Worker) session.get(Worker.class, id);
		tx.commit();
		return worker;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Worker> getAll() {
		List<Worker> lWorkers = new ArrayList<Worker>();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		lWorkers.addAll(session.createCriteria(Worker.class)
				.setResultTransformer( DistinctRootEntityResultTransformer.INSTANCE )
				.list());
		tx.commit();
		return lWorkers;
	}

}
