package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;

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

	@Autowired
	protected SessionFactory sessionFactory;

	public void create(Customer t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	public void update(Customer t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	public void delete(Customer t) {
		sessionFactory.getCurrentSession().delete(t);
	}

	public Customer findByPK(long id) {
		return (Customer) sessionFactory.getCurrentSession().get(Customer.class, id);
	}

	public Customer findByFullName(String f_name, String l_name) {
		return (Customer) sessionFactory.getCurrentSession().createCriteria(Customer.class)
				.add(Restrictions.eq("fName", f_name)).add(Restrictions.eq("lName", l_name)).uniqueResult();
	}

	public Customer findByUser(User user) {
		return (Customer) sessionFactory.getCurrentSession().createCriteria(Customer.class)
				.add(Restrictions.eq("userId", user.getUserId())).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Customer> getAll() {
		List<Customer> customers = new ArrayList<>();
		customers.addAll(sessionFactory.getCurrentSession().createCriteria(Customer.class)
				.setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE).list());

		return customers;
	}

}
