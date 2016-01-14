package com.nixsolutions.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nixsolutions.dao.CustomerDao;
import com.nixsolutions.entity.Customer;

/**
 * @author mixeyes
 *
 */

@Repository("customerDao")
@Transactional
public class CustomerDaoImpl implements CustomerDao {

	private final static Logger logger = LogManager.getLogger();
	@Autowired
	SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.dao.CustomerDao#getAllCustomers()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> customers = null;
		try {
			customers = sessionFactory.getCurrentSession().createCriteria(Customer.class).list();
		} catch (Exception ex) {
			logger.error(ex);
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
	public Customer getCustomerByUserID(Integer userId) {
		Customer customer = null;
		try {
			customer = (Customer) sessionFactory.getCurrentSession().createCriteria(Customer.class)
					.add(Restrictions.eq("user.user_id", userId)).uniqueResult();
		} catch (Exception ex) {
			logger.error(ex);
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
		Customer customer = null;
		try {
			customer = (Customer) sessionFactory.getCurrentSession().createCriteria(Customer.class)
					.add(Restrictions.eq("phone", phone)).uniqueResult();
		} catch (Exception ex) {
			logger.error(ex);
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
		try {
			sessionFactory.getCurrentSession().save(customer);
		} catch (Exception ex) {
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
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(customer);
		} catch (Exception ex) {
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
		try {
			sessionFactory.getCurrentSession().delete(customer);
		} catch (Exception ex) {
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
	public Customer getCustomerByID(Integer customerId) {
		Customer customer = null;
		try {
			customer = (Customer) sessionFactory.getCurrentSession().createCriteria(Customer.class)
					.add(Restrictions.eq("customer_id", customerId.longValue())).uniqueResult();
		} catch (Exception ex) {
			logger.error(ex);
		}
		return customer;
	}
}
