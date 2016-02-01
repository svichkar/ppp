package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.transform.DistinctRootEntityResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.dao.WorkerDAO;
import com.nixsolutions.entities.Worker;
import com.nixsolutions.error.CustomDataException;

@Repository
@Transactional
public class WorkerDAOImpl implements WorkerDAO<Worker> {

	private final static Logger LOG = LogManager.getLogger(WorkerDAOImpl.class);
	@Autowired
	protected SessionFactory sessionFactory;


	@Override
	public void create(Worker t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public void update(Worker t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public void delete(Worker t) {
		sessionFactory.getCurrentSession().delete(t);
	}

	@Override
	public Worker findByPK(long id) {
		return (Worker) sessionFactory.getCurrentSession().get(Worker.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Worker> getAll() {
		List<Worker> workers = new ArrayList<Worker>();
		workers.addAll(sessionFactory.getCurrentSession().createCriteria(Worker.class)
				.setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE).list());
		return workers;
	}

}
