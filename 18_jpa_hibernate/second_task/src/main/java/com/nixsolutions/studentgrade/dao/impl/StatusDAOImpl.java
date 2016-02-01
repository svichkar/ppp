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
		try {
			session.save(status);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateStatus(Status status) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(status);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteStatus(Status status) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(status);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new RuntimeException(e);
		}
	}

	@Override
	public Status findStatusById(Long statusId) {
		Session session = sessionFactory.getCurrentSession();
		Status status = null;
		Transaction transaction = session.beginTransaction();
		try {
			status = (Status) session.get(Status.class, statusId);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new RuntimeException(e);
		}
		return status;
	}

	@Override
	public List<Status> findAllStatuses() {
		Session session = sessionFactory.getCurrentSession();
		List<Status> statuses = new ArrayList<Status>();
		Transaction transaction = session.beginTransaction();
		try {
			statuses = session.createCriteria(Status.class).list();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new RuntimeException(e);
		}
		return statuses;
	}
}
