package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nixsolutions.dao.WorkerSpecializationDao;
import com.nixsolutions.entity.WorkerSpecialization;

@Repository("workerSpecializationDao")
@Transactional
public class WorkerSpecializationDaoImpl implements WorkerSpecializationDao {

	private final static Logger logger = LogManager.getLogger();
	@Autowired
	SessionFactory sessionFactory;

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
		workerSpecialization.setSpecializationName(specialization);
		try {
			sessionFactory.getCurrentSession().save(workerSpecialization);
		} catch (Exception ex) {
			logger.error(ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.Worker_specializationDao#
	 * getAllSpecialization()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<WorkerSpecialization> getAllSpecialization() {
		List<WorkerSpecialization> specializations = new ArrayList<WorkerSpecialization>();
		try {
			specializations = sessionFactory.getCurrentSession().createCriteria(WorkerSpecialization.class).list();
		} catch (Exception ex) {
			logger.error(ex);
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
	public WorkerSpecialization getSpecialization(Integer specializationId) {
		WorkerSpecialization specialization = null;
		try {
			specialization = (WorkerSpecialization) sessionFactory.getCurrentSession()
					.createCriteria(WorkerSpecialization.class)
					.add(Restrictions.eq("specialization_id", specializationId)).uniqueResult();
		} catch (Exception ex) {
			logger.error(ex);
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
	public boolean deleteSpecializationByName(String specializationName) {
		WorkerSpecialization specialization = getAllSpecialization().stream()
				.filter(x -> x.getSpecializationName().equalsIgnoreCase(specializationName))
				.collect(Collectors.toList()).get(0);
		Integer id = specialization.getSpecializationId();
		try {
			sessionFactory.getCurrentSession().delete(specialization);
		} catch (Exception ex) {
			logger.error(ex);
		}
		specialization = getSpecialization(id);
		return specialization == null;
	}

}
