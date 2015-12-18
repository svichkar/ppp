package com.nixsolutions.dao.impl;

import com.nixsolutions.dao.StatusDao;
import com.nixsolutions.entity.Status;
import com.nixsolutions.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class StatusDaoImpl implements StatusDao {

	public StatusDaoImpl() {
	}

	public void create(Status status) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.save(status);
		transaction.commit();
	}

	public void update(Status status) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(status);
		transaction.commit();
	}

	public void delete(Status status) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.delete(status);
		transaction.commit();
	}

	public Status getByStatusId(int statusId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Status status = (Status) session.get(Status.class, statusId);
		transaction.commit();
		return status;
	}

	public Status getByStatusName(String statusName) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Criteria c = session.createCriteria(Status.class);
		c.add(Restrictions.eq("statusName", statusName));
		Status status = (Status) c.uniqueResult();
		transaction.commit();
		return status;
	}

	@SuppressWarnings("unchecked")
	public List<Status> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List<Status> toReturn = session.createCriteria(Status.class).list();
		transaction.commit();
		return toReturn;
	}
}