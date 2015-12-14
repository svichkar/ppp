package com.nixsolutions.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.dao.CustomerDAO;
import com.nixsolutions.hibernate.entity.Customer;

@Repository("customerDAO")
@Transactional
public class CustomerDAOImpl implements CustomerDAO {

	public static Logger LOG = LogManager.getLogger(CustomerDAOImpl.class.getName());
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void createFrom(Customer entity) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate("customer", entity);
		} catch (Exception ex) {
			LOG.error(ex);
		}
	}

	@Override
	public void update(Customer entity) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate("customer", entity);
		} catch (Exception ex) {
			LOG.error(ex);
		}
	}

	@Override
	public void delete(Customer entity) {
		try {
			sessionFactory.getCurrentSession().delete("customer", entity);
		} catch (Exception ex) {
			LOG.error(ex);
		}
	}

	@Override
	public Customer getByPK(int id) {
		try {
			return (Customer) sessionFactory.getCurrentSession().byId(Customer.class).load(id);
		} catch (Exception ex) {
			LOG.error(ex);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getAll() {
		try {
			return sessionFactory.getCurrentSession().createCriteria(Customer.class).list();
		} catch (Exception ex) {
			LOG.error(ex);
			return null;
		}
	}
}
