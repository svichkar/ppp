package com.nixsolutions.dao.hibernate;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nixsolutions.dao.PartDAO;
import com.nixsolutions.hibernate.entity.Part;
import com.nixsolutions.hibernate.util.HibernateUtil;

public class PartDaoHibernate implements PartDAO {

	public static Logger LOG = LogManager.getLogger(PartDaoHibernate.class.getName());
	public static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public void createFrom(Part entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(entity);
		tx.commit();
	}

	@Override
	public void update(Part entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(entity);
		tx.commit();
	}

	@Override
	public void delete(Part entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.delete(entity);
		tx.commit();
	}

	@Override
	public Part getByPK(long id) {
		Session session = sessionFactory.getCurrentSession();
		Part part = null;
		Transaction tx = session.beginTransaction();
		part = (Part) session.get(Part.class, id);
		tx.commit();
		return part;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Part> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<Part> partList = null;
		Transaction tx = session.beginTransaction();
		partList = session.createCriteria(Part.class).list();
		tx.commit();
		return partList;
	}

	@Override
	public Part getByPK(int id) {
		return getByPK((long) id);
	}
}
