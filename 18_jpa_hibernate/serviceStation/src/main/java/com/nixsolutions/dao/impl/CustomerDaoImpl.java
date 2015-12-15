package com.nixsolutions.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nixsolutions.dao.CustomerDao;
import com.nixsolutions.entity.Customer;
import com.nixsolutions.util.HibernateUtil;

/**
 * @author mixeyes
 *
 */

public class CustomerDaoImpl implements CustomerDao {

	private final static Logger logger = LogManager.getLogger();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.dao.CustomerDao#getAllCustomers()
	 */
	@Override
	public List<Customer> getAllCustomers() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<Customer> customers = null;
		Transaction tx = session.beginTransaction();
		try {
			customers = session.createCriteria(Customer.class).list();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return customers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.CustomerDao#
	 * getPhoneNumberByLastName(java.lang.String)
	 */
	@Override
	public String getPhoneNumberByLastName(String last_name) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Customer customer = null;
		Transaction tx = session.beginTransaction();
		try {
			customer = (Customer) session.createCriteria(Customer.class).add(Restrictions.eq("last_name", last_name))
					.uniqueResult();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return customer.getPhone();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.CustomerDao#
	 * getPhoneNumberByLastName(java.lang.String)
	 */
	@Override
	public Customer getCustomerByUserID(Integer user_id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Customer customer = null;
		Transaction tx = session.beginTransaction();
		try {
			customer = (Customer) session.createCriteria(Customer.class).add(Restrictions.eq("user.user_id", user_id))
					.uniqueResult();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return customer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.CustomerDao#
	 * getCustomerByPhoneNumber(java.lang.String)
	 */
	@Override
	public Customer getCustomerByPhoneNumber(String phone) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Customer customer = null;
		Transaction tx = session.beginTransaction();
		try {
			customer = (Customer) session.createCriteria(Customer.class).add(Restrictions.eq("phone", phone))
					.uniqueResult();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return customer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.CustomerDao#createNewCustomer(
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void createNewCustomer(Customer customer) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(customer);
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
	 * com.nixsolutions.serviceStation.dAOFabrica.CustomerDao#updateCustomer(com
	 * .nixsolutions.serviceStation.dbObjects.Customer)
	 */
	@Override
	public void updateCustomer(Customer customer) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate(customer);
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
	 * com.nixsolutions.serviceStation.dAOFabrica.CustomerDao#deleteCustomer(
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public void deleteCustomer(Customer customer) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(customer);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.CustomerDao#
	 * getCustomerByPhoneNumber(java.lang.String)
	 */
	@Override
	public Customer getCustomerByID(Integer customer_id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Customer customer = null;
		Transaction tx = session.beginTransaction();
		try {
			customer = (Customer) session.createCriteria(Customer.class)
					.add(Restrictions.eq("customerId", customer_id.longValue())).uniqueResult();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return customer;
	}
}
