package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.DistinctRootEntityResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.dao.CustomerDAO;
import com.nixsolutions.entities.Customer;
import com.nixsolutions.entities.User;

@Repository
@Transactional
public class CustomerDAOImpl implements CustomerDAO {
	private final static Logger LOG = LogManager.getLogger(CustomerDAOImpl.class);
	@Autowired
	protected SessionFactory sessionFactory;

	@Override
	public void create(Customer t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public void update(Customer t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public void delete(Customer t) {
		sessionFactory.getCurrentSession().delete(t);
	}

	@Override
	public Customer findByPK(long id) {
		return (Customer) sessionFactory.getCurrentSession().get(Customer.class, id);
	}

	@Override
	public Customer findByFullName(String f_name, String l_name) {
		return (Customer) sessionFactory.getCurrentSession().createCriteria(Customer.class)
				.add(Restrictions.eq("f_name", f_name)).add(Restrictions.eq("l_name", l_name)).uniqueResult();
	}

	@Override
	public Customer findByUser(User user) {
		return (Customer) sessionFactory.getCurrentSession().createCriteria(Customer.class)
				.add(Restrictions.eq("user_id", user.getUserId())).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getAll() {
		List<Customer> customers = new ArrayList<>();
		customers.addAll(sessionFactory.getCurrentSession().createCriteria(Customer.class)
				.setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE).list());

		return customers;
	}

}
