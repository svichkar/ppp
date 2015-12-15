/**
 * 
 */
package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.UserDao;
import com.nixsolutions.entity.User;
import com.nixsolutions.util.HibernateUtil;

/**
 * @author ������
 *
 */
public class UserDaoImpl implements UserDao {

	private final static Logger logger = LogManager.getLogger();
	private User user;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dbCommon.DBTables#deleteTableWithAllData(
	 * )
	 */
	@Override
	public boolean deleteUserByID(Integer user_id) {
		User user = getUserByID(user_id);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(user);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		}
		user = getUserByID(user_id);
		if (user == null)
			return true;
		else
			return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.UsersDao#validate(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public boolean validate(String user_login, String user_password) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			user = (User) session.createCriteria(User.class).add(Restrictions.eq("userLogin", user_login))
					.add(Restrictions.eq("userPassword", user_password)).uniqueResult();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		} finally {
			if (session.isOpen())
				session.close();
		}
		if (user != null)
			return true;
		else
			return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.UserDao#getAllCustomers()
	 */
	@Override
	public List<User> getAllCustomers() {
		List<User> users = new ArrayList<User>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			users = session.createCriteria(User.class).add(Restrictions.eq("userRole.userRoleName", "customer"))
					.list();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return users;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.UserDao#getAllWorkers()
	 */
	@Override
	public List<User> getAllWorkers() {
		List<User> users = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			users = session.createCriteria(User.class)
					.add(Restrictions.disjunction().add(Restrictions.eq("userRole.userRoleName", "worker"))
							.add(Restrictions.eq("userRole.userRoleName", "manager"))
							.add(Restrictions.eq("userRole.userRoleName", "storekeeper")))
					.list();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return users;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.dao.UserDao#getUserByID(java.lang.Integer)
	 */
	@Override
	public User getUserByID(Integer user_id) {
		User user = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			user = (User) session.createCriteria(User.class).add(Restrictions.eq("userId", user_id.longValue())).uniqueResult();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.dao.UserDao#getUserByID(java.lang.Integer)
	 */
	@Override
	public User getUserByLogin(String user_login) {
		User user = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			user = (User) session.createCriteria(User.class).add(Restrictions.eq("userLogin", user_login))
					.uniqueResult();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.dao.UserDao#updateUser(com.nixsolutions.entity.User)
	 */
	@Override
	public void updateUser(User user) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate(user);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.dao.UserDao#createNewUser(java.lang.String,
	 * java.lang.String, java.lang.Integer)
	 */
	@Override
	public void createNewUser(String user_login, String user_password, Integer user_role_id) {
		User user = new User();
		user.setUser_login(user_login);
		user.setUser_password(user_password);
		user.setUserRole(DaoFactory.getUserRoleDaoImpl().getUserRole(user_role_id));
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(user);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		}finally {
			if(session.isOpen())
				session.close();
		}

	}

	public User getUser() {
		return user;
	}

}
