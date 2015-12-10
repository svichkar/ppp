package com.nixsolutions.dao.hibernate;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nixsolutions.dao.WorkerSpecializationDAO;
import com.nixsolutions.hibernate.entity.WorkerSpecialization;
import com.nixsolutions.hibernate.util.HibernateUtil;

public class WorkerSpecializationDaoHibernate implements WorkerSpecializationDAO {

	public static Logger LOG = LogManager.getLogger(WorkerSpecializationDaoHibernate.class.getName());
	public static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public void createFrom(WorkerSpecialization entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(entity);
		tx.commit();
	}

	@Override
	public void update(WorkerSpecialization entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(entity);
		tx.commit();
	}

	@Override
	public void delete(WorkerSpecialization entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.delete(entity);
		tx.commit();
	}

	@Override
	public WorkerSpecialization getByPK(int id) {
		Session session = sessionFactory.getCurrentSession();
		WorkerSpecialization workerSpecialization = null;
		Transaction tx = session.beginTransaction();
		workerSpecialization = (WorkerSpecialization) session.get(WorkerSpecialization.class, id);
		tx.commit();
		return workerSpecialization;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WorkerSpecialization> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<WorkerSpecialization> workerSpecializationList = null;
		Transaction tx = session.beginTransaction();
		workerSpecializationList = session.createCriteria(WorkerSpecialization.class).list();
		tx.commit();
		return workerSpecializationList;
	}

}
