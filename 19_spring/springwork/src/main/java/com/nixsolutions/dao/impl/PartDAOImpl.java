package com.nixsolutions.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.dao.PartDAO;
import com.nixsolutions.hibernate.entity.Part;

@Repository("partDAO")
@Transactional
public class PartDAOImpl implements PartDAO {

	public static Logger LOG = LogManager.getLogger(PartDAOImpl.class.getName());
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void createFrom(Part entity) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate("part", entity);
		} catch (Exception ex) {
			LOG.error(ex);
		}
	}

	@Override
	public void update(Part entity) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate("part", entity);
		} catch (Exception ex) {
			LOG.error(ex);
		}
	}

	@Override
	public void delete(Part entity) {
		try {
			sessionFactory.getCurrentSession().delete("part", entity);
		} catch (Exception ex) {
			LOG.error(ex);
		}
	}

	@Override
	public Part getByPK(long id) {
		try {
			return (Part) sessionFactory.getCurrentSession().byId(Part.class).load(id);
		} catch (Exception ex) {
			LOG.error(ex);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Part> getAll() {
		try {
			return sessionFactory.getCurrentSession().createCriteria(Part.class).list();
		} catch (Exception ex) {
			LOG.error(ex);
			return null;
		}
	}

	@Override
	public Part getByPK(int id) {
		return getByPK((long) id);
	}
}
