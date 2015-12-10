package com.nixsolutions.dao.hibernate;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nixsolutions.dao.CustomerDAO;
import com.nixsolutions.hibernate.entity.Customer;
import com.nixsolutions.hibernate.util.HibernateUtil;

public class CustomerDaoHibernate implements CustomerDAO {

	public static Logger LOG = LogManager.getLogger(CustomerDaoHibernate.class.getName());
	public static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public void createFrom(Customer entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(entity);
		tx.commit();
	}

	@Override
	public void update(Customer entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(entity);
		tx.commit();
	}

	@Override
	public void delete(Customer entity) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.delete(entity);
		tx.commit();
	}

	@Override
	public Customer getByPK(int id) {
		Session session = sessionFactory.getCurrentSession();
		Customer customer = null;
		Transaction tx = session.beginTransaction();
		customer = (Customer) session.get(Customer.class, id);
		tx.commit();
		return customer;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<Customer> customerList = null;
		Transaction tx = session.beginTransaction();
		customerList = session.createCriteria(Customer.class).list();
		tx.commit();
		return customerList;
	}
}
