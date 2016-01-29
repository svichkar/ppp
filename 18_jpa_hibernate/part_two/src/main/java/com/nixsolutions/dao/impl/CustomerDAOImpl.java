package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.DistinctRootEntityResultTransformer;

import com.nixsolutions.app.HibernateUtil;
import com.nixsolutions.dao.CustomerDAO;
import com.nixsolutions.entities.Customer;
import com.nixsolutions.error.CustomDataException;

public class CustomerDAOImpl implements CustomerDAO<Customer> {
	private final static Logger LOG = LogManager.getLogger(CustomerDAOImpl.class);
	public static SessionFactory sessionFactory;

	public CustomerDAOImpl() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@Override
	public void create(Customer t) {
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
	public void update(Customer t) {
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
	public void delete(Customer t) {
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
	public Customer findByPK(long id) {
		Session session = null;
		Transaction transaction = null;
		Customer customer = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			customer = (Customer) session.get(Customer.class, id);
			transaction.commit();
		} catch (Exception ex) {
			LOG.error(ex, ex);
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
			throw new CustomDataException(ex);
		}
		return customer;
	}

	public Customer findByFullName(String f_name, String l_name) {
		Session session = null;
		Transaction transaction = null;
		Customer customer = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			customer = (Customer) session.createCriteria(Customer.class).add(Restrictions.eq("f_name", f_name))
					.add(Restrictions.eq("l_name", l_name)).uniqueResult();
			transaction.commit();
		} catch (Exception ex) {
			LOG.error(ex, ex);
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
			throw new CustomDataException(ex);
		}
		return customer;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getAll() {
		List<Customer> customers = new ArrayList<>();
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			customers.addAll(session.createCriteria(Customer.class)
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
		return customers;
	}

}
