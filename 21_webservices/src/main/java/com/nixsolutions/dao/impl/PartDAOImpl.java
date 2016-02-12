package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.transform.DistinctRootEntityResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.dao.PartDAO;
import com.nixsolutions.entities.Part;

@Repository
@Transactional
public class PartDAOImpl implements PartDAO {

	@Autowired
	protected SessionFactory sessionFactory;

	public void create(Part t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	public void update(Part t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	public void delete(Part t) {
		sessionFactory.getCurrentSession().delete(t);
	}

	public Part findByPK(long id) {
		return (Part) sessionFactory.getCurrentSession().get(Part.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Part> getAll() {
		List<Part> parts = new ArrayList<>();
		parts.addAll(sessionFactory.getCurrentSession().createCriteria(Part.class)
				.setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE).list());
		return parts;
	}

}
