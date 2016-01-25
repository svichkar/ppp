package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;

import com.nixsolutions.app.HibernateUtil;
import com.nixsolutions.dao.PartDAO;
import com.nixsolutions.entities.Part;

public class PartDAOImpl implements PartDAO<Part> {

	private final static Logger LOG = LogManager.getLogger(PartDAOImpl.class);
	public static SessionFactory sessionFactory;

	public PartDAOImpl() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@Override
	public void create(Part t) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(t);
		tx.commit();
	}

	@Override
	public void update(Part t) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(t);
		tx.commit();
	}

	@Override
	public void delete(Part t) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.delete(t);
		tx.commit();
	}

	@Override
	public Part findByPK(long id) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Part part = (Part) session.get(Part.class, id);
		tx.commit();
		return part;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Part> getAll() {
		List<Part> lParts = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		lParts.addAll(session.createCriteria(Part.class).list());
		tx.commit();
		return lParts;
	}

}
