package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.DistinctRootEntityResultTransformer;

import com.nixsolutions.app.HibernateUtil;
import com.nixsolutions.dao.PartDAO;
import com.nixsolutions.entities.Part;
import com.nixsolutions.error.CustomDataException;

public class PartDAOImpl implements PartDAO<Part> {

	private final static Logger LOG = LogManager.getLogger(PartDAOImpl.class);
	public static SessionFactory sessionFactory;

	public PartDAOImpl() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@Override
	public void create(Part t) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(t);
			transaction.commit();
		} catch (Exception ex) {
			LOG.error(ex, ex);
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
			throw new CustomDataException(ex);
		}
	}

	@Override
	public void update(Part t) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(t);
			transaction.commit();
		} catch (Exception ex) {
			LOG.error(ex, ex);
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
			throw new CustomDataException(ex);
		}
	}

	@Override
	public void delete(Part t) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.delete(t);
			transaction.commit();
		} catch (Exception ex) {
			LOG.error(ex, ex);
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
			throw new CustomDataException(ex);
		}
	}

	@Override
	public Part findByPK(long id) {
		Session session = null;
		Transaction transaction = null;
		Part part = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			part = (Part) session.get(Part.class, id);
			transaction.commit();

		} catch (Exception ex) {
			LOG.error(ex, ex);
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
			throw new CustomDataException(ex);
		}
		return part;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Part> getAll() {
		List<Part> parts = new ArrayList<>();
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			parts.addAll(session.createCriteria(Part.class)
					.setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE).list());
			transaction.commit();
		} catch (Exception ex) {
			LOG.error(ex, ex);
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
			throw new CustomDataException(ex);
		}
		return parts;
	}

}
