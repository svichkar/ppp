package com.nixsolutions.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.dao.WorkerDAO;
import com.nixsolutions.hibernate.entity.Worker;

@Repository("workerDAO")
@Transactional
public class WorkerDAOImpl implements WorkerDAO {

	public static Logger LOG = LogManager.getLogger(WorkerDAOImpl.class.getName());
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void createFrom(Worker entity) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate("worker", entity);
		} catch (Exception ex) {
			LOG.error(ex);
		}
	}

	@Override
	public void update(Worker entity) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate("worker", entity);
		} catch (Exception ex) {
			LOG.error(ex);
		}
	}

	@Override
	public void delete(Worker entity) {
		try {
			sessionFactory.getCurrentSession().delete("worker", entity);
		} catch (Exception ex) {
			LOG.error(ex);
		}
	}

	@Override
	public Worker getByPK(int id) {
		try {
			return (Worker) sessionFactory.getCurrentSession().byId(Worker.class).load(id);
		} catch (Exception ex) {
			LOG.error(ex);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Worker> getAll() {
		try {
			return sessionFactory.getCurrentSession().createCriteria(Worker.class).list();
		} catch (Exception ex) {
			LOG.error(ex);
			return null;
		}
	}

}
