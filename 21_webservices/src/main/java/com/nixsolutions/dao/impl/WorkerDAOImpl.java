package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.transform.DistinctRootEntityResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.dao.WorkerDAO;
import com.nixsolutions.entities.Worker;

@Repository
@Transactional
public class WorkerDAOImpl implements WorkerDAO {

	@Autowired
	protected SessionFactory sessionFactory;

	public void create(Worker t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	public void update(Worker t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	public void delete(Worker t) {
		sessionFactory.getCurrentSession().delete(t);
	}

	public Worker findByPK(long id) {
		return (Worker) sessionFactory.getCurrentSession().get(Worker.class, id);
	}

	@SuppressWarnings("unchecked")

	public List<Worker> getAll() {
		List<Worker> workers = new ArrayList<Worker>();
		workers.addAll(sessionFactory.getCurrentSession().createCriteria(Worker.class)
				.setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE).list());
		return workers;
	}

}
