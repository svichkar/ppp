package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nixsolutions.app.HibernateUtil;
import com.nixsolutions.dao.WorkerSpecificationDAO;
import com.nixsolutions.entities.WorkerSpecification;

public class WorkerSpecificationDAOImpl implements WorkerSpecificationDAO<WorkerSpecification> {

	private final static Logger LOG = LogManager.getLogger(WorkerSpecificationDAOImpl.class);
	public static SessionFactory sessionFactory;

	public WorkerSpecificationDAOImpl() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@Override
	public void create(WorkerSpecification t) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(t);
		tx.commit();
	}

	@Override
	public void update(WorkerSpecification t) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(t);
		tx.commit();
	}

	@Override
	public void delete(WorkerSpecification t) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.delete(t);
		tx.commit();
	}

	@Override
	public WorkerSpecification findByPK(long id) {
		WorkerSpecification ws = new WorkerSpecification();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		ws = (WorkerSpecification) session.get(WorkerSpecification.class, id);
		tx.commit();
		return ws;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<WorkerSpecification> getAll() {
		List<WorkerSpecification> lWS = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		lWS.addAll(session.createCriteria(WorkerSpecification.class).list());
		tx.commit();
		return lWS;
	}

}
