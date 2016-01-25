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
		UserCustomerRole ucr = new UserCustomerRole();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Customer customer = (Customer) session.createCriteria(Customer.class)
				.add(Restrictions.eq("user_id", user.getUserId())).uniqueResult();
		tx.commit();
		ucr.setCustomerId(customer.getCustomerId());
		ucr.setF_name(customer.getF_name());
		ucr.setL_name(customer.getL_name());
		ucr.setCustomerId(customer.getCustomerId());
		ucr.setPassword(customer.getUser().getPassword());
		ucr.setRoleId(customer.getUser().getRole().getRoleId());
		ucr.setRole(customer.getUser().getRole().getRolename());
		ucr.setUser_id(customer.getUser().getUserId());
		ucr.setUsername(customer.getUser().getUsername());
		return ucr;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserCustomerRole> getAll() {
		List<UserCustomerRole> listResult = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		List<Customer> listCustomers = session.createCriteria(Customer.class).list();
		tx.commit();
		for (Customer customer : listCustomers) {
			UserCustomerRole ucr = new UserCustomerRole();
			ucr.setCustomerId(customer.getCustomerId());
			ucr.setF_name(customer.getF_name());
			ucr.setL_name(customer.getL_name());
			ucr.setCustomerId(customer.getCustomerId());
			ucr.setPassword(customer.getUser().getPassword());
			ucr.setRoleId(customer.getUser().getRole().getRoleId());
			ucr.setRole(customer.getUser().getRole().getRolename());
			ucr.setUser_id(customer.getUser().getUserId());
			ucr.setUsername(customer.getUser().getUsername());
			listResult.add(ucr);
		}
		return listResult;
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
	public UserCustomerRole findByPK(long id) {
		UserCustomerRole ucr = new UserCustomerRole();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Customer customer = (Customer) session.createCriteria(Customer.class)
				.add(Restrictions.eq("user_id", id)).uniqueResult();
		tx.commit();
		ucr.setCustomerId(customer.getCustomerId());
		ucr.setF_name(customer.getF_name());
		ucr.setL_name(customer.getL_name());
		ucr.setCustomerId(customer.getCustomerId());
		ucr.setPassword(customer.getUser().getPassword());
		ucr.setRoleId(customer.getUser().getRole().getRoleId());
		ucr.setRole(customer.getUser().getRole().getRolename());
		ucr.setUser_id(customer.getUser().getUserId());
		ucr.setUsername(customer.getUser().getUsername());
		return ucr;
	}

}
