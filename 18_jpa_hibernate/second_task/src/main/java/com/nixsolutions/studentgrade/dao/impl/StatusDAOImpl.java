package com.nixsolutions.studentgrade.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nixsolutions.studentgrade.entity.Status;
import com.nixsolutions.studentgrade.dao.StatusDAO;
import com.nixsolutions.studentgrade.util.HibernateUtil;

public class StatusDAOImpl implements StatusDAO {

	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public void createStatus(Status status) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.save(status);
		transaction.commit();
	}

	@Override
	public void updateStatus(Status status) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.update(status);
		transaction.commit();
	}

	@Override
	public void deleteStatus(Status status) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(status);
			transaction.commit();
		} catch (Exception ex) {
			transaction.rollback();
		} 
	}

	@Override
	public Status findStatusById(Long statusId) {
		Session session = sessionFactory.getCurrentSession();
		Status status = null;
		Transaction transaction = session.beginTransaction();
		status = (Status) session.get(Status.class, statusId);
		transaction.commit();
		return status;
	}

	@Override
	public List<Status> findAllStatuses() {
		Session session = sessionFactory.getCurrentSession();
		List<Status> statuses = new ArrayList<Status>();
		Transaction transaction = session.beginTransaction();
		statuses = session.createCriteria(Status.class).list();
		transaction.commit();
		return statuses;
	}
}
