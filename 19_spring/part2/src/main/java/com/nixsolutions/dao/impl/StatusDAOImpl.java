package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.dao.StatusDAO;
import com.nixsolutions.entities.Status;

@Repository
@Transactional
public class StatusDAOImpl implements StatusDAO {

	private final static Logger LOG = LogManager.getLogger(StatusDAOImpl.class);
	@Autowired
	protected SessionFactory sessionFactory;


	@Override
	public void create(Status t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public void update(Status t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public void delete(Status t) {
		sessionFactory.getCurrentSession().delete(t);
	}

	@Override
	public Status findByPK(long id) {
		return (Status) sessionFactory.getCurrentSession().get(Status.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Status> getAll() {
		List<Status> statuses = new ArrayList<Status>();
		statuses.addAll(sessionFactory.getCurrentSession().createCriteria(Status.class).list());
		return statuses;
	}
}
