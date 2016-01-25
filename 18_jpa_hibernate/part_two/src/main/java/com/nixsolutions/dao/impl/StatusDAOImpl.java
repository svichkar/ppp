package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nixsolutions.app.HibernateUtil;
import com.nixsolutions.dao.StatusDAO;
import com.nixsolutions.entities.OrderInWork;
import com.nixsolutions.entities.Status;

public class StatusDAOImpl implements StatusDAO<Status> {

	private final static Logger LOG = LogManager.getLogger(StatusDAOImpl.class);
	public static SessionFactory sessionFactory;

	public StatusDAOImpl() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@Override
	public void create(Status t) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(t);
		tx.commit();
	}

	@Override
	public void update(Status t) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(t);
		tx.commit();
	}

	@Override
	public void delete(Status t) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.delete(t);
		tx.commit();
	}

	@Override
	public Status findByPK(long id) {
		Status status = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		status = (Status) session.get(Status.class, id);
		tx.commit();
		return status;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Status> getAll() {
		List<Status> lStatus = new ArrayList<Status>();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		lStatus.addAll(session.createCriteria(Status.class).list());
		tx.commit();
		return lStatus;
	}
}
