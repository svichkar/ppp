package com.nixsolutions.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.dao.StatusDAO;
import com.nixsolutions.hibernate.entity.Status;

@Repository("statusDAO")
@Transactional
public class StatusDAOImpl implements StatusDAO {

	public static Logger LOG = LogManager.getLogger(StatusDAOImpl.class.getName());
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void createFrom(Status entity) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate("status", entity);
		} catch (Exception ex) {
			LOG.error(ex);
		}
	}

	@Override
	public void update(Status entity) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate("status", entity);
		} catch (Exception ex) {
			LOG.error(ex);
		}
	}

	@Override
	public void delete(Status entity) {
		try {
			sessionFactory.getCurrentSession().delete("status", entity);
		} catch (Exception ex) {
			LOG.error(ex);
		}
	}

	@Override
	public Status getByPK(int id) {
		try {
			return (Status) sessionFactory.getCurrentSession().byId(Status.class).load(id);
		} catch (Exception ex) {
			LOG.error(ex);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Status> getAll() {
		try {
			return sessionFactory.getCurrentSession().createCriteria(Status.class).list();
		} catch (Exception ex) {
			LOG.error(ex);
			return null;
		}
	}
}
