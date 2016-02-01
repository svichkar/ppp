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

import com.nixsolutions.dao.PartDAO;
import com.nixsolutions.entities.Part;

@Repository
@Transactional
public class PartDAOImpl implements PartDAO<Part> {

	private final static Logger LOG = LogManager.getLogger(PartDAOImpl.class);
	@Autowired
	protected SessionFactory sessionFactory;


	@Override
	public void create(Part t) {
		sessionFactory.getCurrentSession().beginTransaction();
	}

	@Override
	public void update(Part t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public void delete(Part t) {
		sessionFactory.getCurrentSession().delete(t);
	}

	@Override
	public Part findByPK(long id) {
		return (Part) sessionFactory.getCurrentSession().get(Part.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Part> getAll() {
		List<Part> parts = new ArrayList<>();
		parts.addAll(sessionFactory.getCurrentSession().createCriteria(Part.class)
				.setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE).list());
		return parts;
	}

}
