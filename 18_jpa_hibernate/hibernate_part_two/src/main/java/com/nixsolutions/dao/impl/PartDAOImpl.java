package com.nixsolutions.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nixsolutions.dao.PartDAO;
import com.nixsolutions.hibernate.entity.Part;
import com.nixsolutions.hibernate.util.HibernateUtil;

public class PartDAOImpl implements PartDAO {

	public static Logger LOG = LogManager.getLogger(PartDAOImpl.class.getName());
	public static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public Part createFrom(Part entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Part part = null;
		try {
			session.saveOrUpdate("part", entity);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		session = sessionFactory.getCurrentSession();
		tx = session.beginTransaction();
		try {
			part = (Part) session.get(Part.class, entity.getPartId());
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return part;
	}

	@Override
	public void update(Part entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate("part", entity);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
	}

	@Override
	public void delete(Part entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete("part", entity);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
	}

	@Override
	public Part getByPK(int id) {
		Session session = sessionFactory.getCurrentSession();
		Part part = null;
		Transaction tx = session.beginTransaction();
		try {
			part = (Part) session.byId(Part.class).load(id);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return part;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Part> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<Part> partList = null;
		Transaction tx = session.beginTransaction();
		try {
			partList = session.createCriteria(Part.class).list();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return partList;
	}
}
