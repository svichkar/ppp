package com.nixsolutions.bean;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nixsolutions.app.HibernateUtil;
import com.nixsolutions.dao.GenericDao;
import com.nixsolutions.entities.Customer;
import com.nixsolutions.entities.User;
import com.nixsolutions.entities.UserCustomerRole;

public class UserCustomerRoleBean implements GenericDao<UserCustomerRole> {

	private final static Logger LOG = LogManager.getLogger(UserCustomerRoleBean.class);
	public static SessionFactory sessionFactory;

	public UserCustomerRoleBean() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	public UserCustomerRole findByUser(User user) {
		UserCustomerRole userCustomerRole = new UserCustomerRole();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		Customer customer = null;
		try {
			transaction = session.beginTransaction();
			customer = (Customer) session.createCriteria(Customer.class)
					.add(Restrictions.eq("user_id", user.getUserId())).uniqueResult();
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
		}
		if (customer != null) {

			userCustomerRole.setCustomerId(customer.getCustomerId());
			userCustomerRole.setF_name(customer.getF_name());
			userCustomerRole.setL_name(customer.getL_name());
			userCustomerRole.setCustomerId(customer.getCustomerId());
			userCustomerRole.setPassword(customer.getUser().getPassword());
			userCustomerRole.setRoleId(customer.getUser().getRole().getRoleId());
			userCustomerRole.setRole(customer.getUser().getRole().getRolename());
			userCustomerRole.setUser_id(customer.getUser().getUserId());
			userCustomerRole.setUsername(customer.getUser().getUsername());
		}
		return userCustomerRole;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserCustomerRole> getAll() {
		List<UserCustomerRole> results = new ArrayList<>();
		List<Customer> customers = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			customers = session.createCriteria(Customer.class).list();
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
		}
		for (Customer customer : customers) {
			UserCustomerRole userCustomerRole = new UserCustomerRole();
			userCustomerRole.setCustomerId(customer.getCustomerId());
			userCustomerRole.setF_name(customer.getF_name());
			userCustomerRole.setL_name(customer.getL_name());
			userCustomerRole.setCustomerId(customer.getCustomerId());
			userCustomerRole.setPassword(customer.getUser().getPassword());
			userCustomerRole.setRoleId(customer.getUser().getRole().getRoleId());
			userCustomerRole.setRole(customer.getUser().getRole().getRolename());
			userCustomerRole.setUser_id(customer.getUser().getUserId());
			userCustomerRole.setUsername(customer.getUser().getUsername());
			results.add(userCustomerRole);
		}
		return results;
	}

	@Deprecated
	@Override
	public void create(UserCustomerRole t) {

	}

	@Deprecated
	@Override
	public void update(UserCustomerRole t) {

	}

	@Deprecated
	@Override
	public void delete(UserCustomerRole t) {

	}

	@Override
	public UserCustomerRole findByPK(long userid) {
		UserCustomerRole userCustomerRole = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		Customer customer = null;
		try {
			transaction = session.beginTransaction();
			customer = (Customer) session.createCriteria(Customer.class).add(Restrictions.eq("user_id", userid))
					.uniqueResult();
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
		}
		if (customer != null) {
			userCustomerRole = new UserCustomerRole();
			userCustomerRole.setCustomerId(customer.getCustomerId());
			userCustomerRole.setF_name(customer.getF_name());
			userCustomerRole.setL_name(customer.getL_name());
			userCustomerRole.setCustomerId(customer.getCustomerId());
			userCustomerRole.setPassword(customer.getUser().getPassword());
			userCustomerRole.setRoleId(customer.getUser().getRole().getRoleId());
			userCustomerRole.setRole(customer.getUser().getRole().getRolename());
			userCustomerRole.setUser_id(customer.getUser().getUserId());
			userCustomerRole.setUsername(customer.getUser().getUsername());
		}
		return userCustomerRole;
	}

}
