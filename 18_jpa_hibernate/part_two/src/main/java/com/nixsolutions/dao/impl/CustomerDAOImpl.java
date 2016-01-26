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

public class CustomerDAOImpl implements CustomerDAO<Customer> {
	private final static Logger LOG = LogManager.getLogger(CustomerDAOImpl.class);
	public static SessionFactory sessionFactory;

	public CustomerDAOImpl() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@Override
	public void create(Customer t) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(t);
		tx.commit();
	}

	@Override
	public void update(Customer t) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(t);
		tx.commit();
	}

	@Override
	public void delete(Customer t) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.delete(t);
		tx.commit();
	}

	@Override
	public Customer findByPK(long id) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Customer customer = (Customer) session.get(Customer.class, id);
		tx.commit();
		return customer;
	}

	public Customer findByFullName(String f_name, String l_name) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Customer customer = (Customer) session.createCriteria(Customer.class)
				.add(Restrictions.eq("f_name", f_name))
				.add(Restrictions.eq("l_name", l_name)).uniqueResult();
		tx.commit();
		return customer;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getAll() {
		List<Customer> lCustomers = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		lCustomers.addAll(session.createCriteria(Customer.class)
				.setResultTransformer( DistinctRootEntityResultTransformer.INSTANCE )
				.list());
		tx.commit();
		return lCustomers;
	}

}
