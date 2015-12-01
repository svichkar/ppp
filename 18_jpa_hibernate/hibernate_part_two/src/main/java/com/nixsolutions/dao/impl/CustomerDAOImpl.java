package com.nixsolutions.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nixsolutions.dao.CustomerDAO;
import com.nixsolutions.hibernate.entity.Customer;
import com.nixsolutions.hibernate.util.HibernateUtil;

public class CustomerDAOImpl implements CustomerDAO {

	public static Logger LOG = LogManager.getLogger(CustomerDAOImpl.class.getName());
	public static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public Customer createFrom(Customer entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Customer customer = null;
		try {
			session.saveOrUpdate("customer", entity);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		session = sessionFactory.getCurrentSession();
		tx = session.beginTransaction();
		try {
			customer = (Customer) session.get(Customer.class, entity.getCustomerId());
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return customer;
	}

	@Override
	public void update(Customer entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate("customer", entity);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
	}

	@Override
	public void delete(Customer entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete("customer", entity);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
	}

	@Override
	public Customer getByPK(int id) {
		Session session = sessionFactory.getCurrentSession();
		Customer customer = null;
		Transaction tx = session.beginTransaction();
		try {
			customer = (Customer) session.byId(Customer.class).load(id);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return customer;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<Customer> customerList = null;
		Transaction tx = session.beginTransaction();
		try {
			customerList = session.createCriteria(Customer.class).list();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			LOG.error(ex);
		}
		return customerList;
	}
}
