package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.dao.WorkerSpecificationDAO;
import com.nixsolutions.entities.WorkerSpecification;

@Repository
@Transactional
public class WorkerSpecificationDAOImpl implements WorkerSpecificationDAO {

	@Autowired
	protected SessionFactory sessionFactory;

	public void create(WorkerSpecification t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	public void update(WorkerSpecification t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	public void delete(WorkerSpecification t) {
		sessionFactory.getCurrentSession().delete(t);
	}

	public WorkerSpecification findByPK(long id) {
		return (WorkerSpecification) sessionFactory.getCurrentSession().get(WorkerSpecification.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<WorkerSpecification> getAll() {
		List<WorkerSpecification> workerSpecifications = new ArrayList<>();
		workerSpecifications
				.addAll(sessionFactory.getCurrentSession().createCriteria(WorkerSpecification.class).list());
		return workerSpecifications;
	}

}
