package com.nixsolutions.dao.hibernate;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nixsolutions.dao.StatusDAO;
import com.nixsolutions.hibernate.entity.Status;
import com.nixsolutions.hibernate.util.HibernateUtil;

public class StatusDaoHibernate implements StatusDAO {

	public static Logger LOG = LogManager.getLogger(StatusDaoHibernate.class.getName());
	public static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public void createFrom(Status entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(entity);
		tx.commit();
	}

	@Override
	public void update(Status entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(entity);
		tx.commit();
	}

	@Override
	public void delete(Status entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.delete(entity);
		tx.commit();
	}

	@Override
	public Status getByPK(int id) {
		Session session = sessionFactory.getCurrentSession();
		Status status = null;
		Transaction tx = session.beginTransaction();
		status = (Status) session.get(Status.class, id);
		tx.commit();
		return status;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Status> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<Status> statusList = null;
		Transaction tx = session.beginTransaction();
		statusList = session.createCriteria(Status.class).list();
		tx.commit();
		return statusList;
	}
}
