/**
 * 
 */
package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nixsolutions.dao.PartDao;
import com.nixsolutions.entity.Part;

/**
 * @author mixeyes
 *
 */
@Repository("partDao")
@Transactional
public class PartDaoImpl implements PartDao {

	private final static Logger logger = LogManager.getLogger();
	@Autowired
	SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.PartDao#getAllParts()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Part> getAllParts() {
		List<Part> parts = new ArrayList<Part>();
		try {
			parts = sessionFactory.getCurrentSession().createCriteria(Part.class).list();
		} catch (Exception ex) {
			logger.error(ex);
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
		Part part = null;
		try {
			part = (Part) sessionFactory.getCurrentSession().createCriteria(Part.class)
					.add(Restrictions.eq("part_name", part_name)).add(Restrictions.eq("vendor", vendor)).uniqueResult();
		} catch (Exception ex) {
			logger.error(ex);
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
		Part part = null;
		try {
			part = (Part) sessionFactory.getCurrentSession().createCriteria(Part.class)
					.add(Restrictions.eq("partId", part_id)).uniqueResult();
		} catch (Exception ex) {
			logger.error(ex);
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
		try {
			sessionFactory.getCurrentSession().save(part);
		} catch (Exception ex) {
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
		Part part = getPart(part_id);
		try {
			sessionFactory.getCurrentSession().delete(part);
		} catch (Exception ex) {
			logger.error(ex);
		}
		part = getPart(part_id);
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
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(part);
		} catch (Exception ex) {
			logger.error(ex);
		}
	}
}
