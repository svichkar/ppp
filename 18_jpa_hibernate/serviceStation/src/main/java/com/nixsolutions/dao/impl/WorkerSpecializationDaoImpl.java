package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nixsolutions.dao.WorkerSpecializationDao;
import com.nixsolutions.entity.WorkerSpecialization;
import com.nixsolutions.util.HibernateUtil;

public class WorkerSpecializationDaoImpl implements WorkerSpecializationDao {

	private final static Logger logger = LogManager.getLogger();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.dao.WorkerSpecializationDao#createNewSpecialization(java
	 * .lang.String)
	 */
	@Override
	public void createNewSpecialization(String specialization) {
		WorkerSpecialization workerSpecialization = new WorkerSpecialization();
		workerSpecialization.setSpecialization_name(specialization);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(workerSpecialization);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.Worker_specializationDao#
	 * getAllSpecialization()
	 */
	@Override
	public List<WorkerSpecialization> getAllSpecialization() {
		List<WorkerSpecialization> specializations = new ArrayList<WorkerSpecialization>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			specializations = session.createCriteria(WorkerSpecialization.class).list();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return specializations;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.Worker_specializationDao#
	 * getSpecialization(java.lang.Integer)
	 */
	@Override
	public WorkerSpecialization getSpecialization(Integer specialization_id) {
		WorkerSpecialization specialization = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			specialization = (WorkerSpecialization) session.createCriteria(WorkerSpecialization.class)
					.add(Restrictions.eq("specializationId", specialization_id)).uniqueResult();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return specialization;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.dao.WorkerSpecializationDao#deleteSpecializationByName(
	 * java.lang.String)
	 */
	@Override
	public boolean deleteSpecializationByName(String specialization_name) {
		WorkerSpecialization specialization = getAllSpecialization().stream()
				.filter(x -> x.getSpecialization_name().equalsIgnoreCase(specialization_name))
				.collect(Collectors.toList()).get(0);
		Integer id = specialization.getSpecialization_id();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(specialization);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		}
		specialization = getSpecialization(id);
		if (specialization == null)
			return true;
		else
			return false;
	}

}
