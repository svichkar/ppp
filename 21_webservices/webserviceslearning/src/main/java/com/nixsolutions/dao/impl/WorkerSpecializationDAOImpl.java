package com.nixsolutions.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.dao.WorkerSpecializationDAO;
import com.nixsolutions.hibernate.entity.WorkerSpecialization;

@Repository("workerSpecializationDAO")
@Transactional
public class WorkerSpecializationDAOImpl implements WorkerSpecializationDAO {

	public static Logger LOG = LogManager.getLogger(WorkerSpecializationDAOImpl.class.getName());
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void createFrom(WorkerSpecialization entity) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate("workerSpecialization", entity);
		} catch (Exception ex) {
			LOG.error(ex);
		}
	}

	@Override
	public void update(WorkerSpecialization entity) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate("workerSpecialization", entity);
		} catch (Exception ex) {
			LOG.error(ex);
		}
	}

	@Override
	public void delete(WorkerSpecialization entity) {
		try {
			sessionFactory.getCurrentSession().delete("workerSpecialization", entity);
		} catch (Exception ex) {
			LOG.error(ex);
		}
	}

	@Override
	public WorkerSpecialization getByPK(int id) {
		try {
			return (WorkerSpecialization) sessionFactory.getCurrentSession().byId(WorkerSpecialization.class).load(id);
		} catch (Exception ex) {
			LOG.error(ex);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WorkerSpecialization> getAll() {
		try {
			return sessionFactory.getCurrentSession().createCriteria(WorkerSpecialization.class).list();
		} catch (Exception ex) {
			LOG.error(ex);
			return null;
		}
	}

}
