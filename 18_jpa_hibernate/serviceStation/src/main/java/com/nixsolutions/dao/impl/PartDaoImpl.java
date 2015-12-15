/**
 * 
 */
package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.PartDao;
import com.nixsolutions.entity.Part;
import com.nixsolutions.util.HibernateUtil;

/**
 * @author mixeyes
 *
 */
public class PartDaoImpl implements PartDao {

	private final static Logger logger = LogManager.getLogger();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.PartDao#getAllParts()
	 */
	@Override
	public List<Part> getAllParts() {
		List<Part> parts = new ArrayList<Part>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			parts = session.createCriteria(Part.class).list();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return parts;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.PartDao#getPart(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public Part getPart(String part_name, String vendor) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Part part = null;
		Transaction tx = session.beginTransaction();
		try {
			part = (Part) session.createCriteria(Part.class).add(Restrictions.eq("part_name", part_name))
					.add(Restrictions.eq("vendor", vendor)).uniqueResult();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return part;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.PartDao#getPart(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public Part getPart(Integer part_id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Part part = null;
		Transaction tx = session.beginTransaction();
		try {
			part = (Part) session.createCriteria(Part.class).add(Restrictions.eq("partId", part_id)).uniqueResult();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return part;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.PartDao#addNewPart(java.lang.
	 * String, java.lang.String, java.lang.Integer)
	 */
	@Override
	public void addNewPart(String partName, String vendor, Integer amount) {
		Part part = new Part();
		part.setAmount(amount);
		part.setPart_name(partName);
		part.setVendor(vendor);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(part);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.PartDao#deletePartByID(java.
	 * lang.Integer)
	 */
	@Override
	public boolean deletePartByID(Integer part_id) {
		Part part = DaoFactory.getPartDaoImpl().getPart(part_id);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(part);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		}
		part = DaoFactory.getPartDaoImpl().getPart(part_id);
		if (part == null)
			return true;
		else
			return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.dao.PartDao#updateExistingPart(com.nixsolutions.entity.
	 * Part)
	 */
	@Override
	public void updateExistingPart(Part part) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate(part);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		}
	}
}
