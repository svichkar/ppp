package com.nixsolutions.dao.impl;

import com.nixsolutions.dao.StatusDao;
import com.nixsolutions.entity.Status;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class StatusDaoImpl implements StatusDao {

	@Autowired
	private SessionFactory sessionFactory;

	public StatusDaoImpl() {
	}

	public void create(Status status) {
		sessionFactory.getCurrentSession().save(status);
	}

	public void update(Status status) {
		sessionFactory.getCurrentSession().saveOrUpdate(status);
	}

	public void delete(Status status) {
		sessionFactory.getCurrentSession().delete(status);
	}

	public Status getByStatusId(int statusId) {
		return (Status) sessionFactory.getCurrentSession().get(Status.class, statusId);
	}

	public Status getByStatusName(String statusName) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Status.class);
		c.add(Restrictions.eq("statusName", statusName));
		return (Status) c.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Status> getAll() {
		return sessionFactory.getCurrentSession().createCriteria(Status.class).list();
	}
}