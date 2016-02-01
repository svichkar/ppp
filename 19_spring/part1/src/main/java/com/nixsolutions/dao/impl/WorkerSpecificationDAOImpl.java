package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.dao.WorkerSpecificationDAO;
import com.nixsolutions.entities.WorkerSpecification;

@Repository
@Transactional
public class WorkerSpecificationDAOImpl implements WorkerSpecificationDAO<WorkerSpecification> {

	private final static Logger LOG = LogManager.getLogger(WorkerSpecificationDAOImpl.class);
	@Autowired
	protected SessionFactory sessionFactory;

	@Override
	public void create(WorkerSpecification t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public void update(WorkerSpecification t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public void delete(WorkerSpecification t) {
		sessionFactory.getCurrentSession().delete(t);
	}

	@Override
	public WorkerSpecification findByPK(long id) {
		return (WorkerSpecification) sessionFactory.getCurrentSession().get(WorkerSpecification.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WorkerSpecification> getAll() {
		List<WorkerSpecification> workerSpecifications = new ArrayList<>();
		workerSpecifications
				.addAll(sessionFactory.getCurrentSession().createCriteria(WorkerSpecification.class).list());
		return workerSpecifications;
	}

}
