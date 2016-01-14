/**
 * 
 */
package com.nixsolutions.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nixsolutions.dao.UserDao;
import com.nixsolutions.entity.User;
import com.nixsolutions.entity.UserRole;

/**
 * 
 *
 */
@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao {

	private final static Logger logger = LogManager.getLogger();
	private User user;
	@Autowired
	SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dbCommon.DBTables#deleteTableWithAllData(
	 * )
	 */
	@Override
	public boolean deleteUserByID(Integer userId) {
		User user = getUserByID(userId);
		try {
			sessionFactory.getCurrentSession().delete(user);
		} catch (Exception ex) {
			logger.error(ex);
		}
		user = getUserByID(userId);
		return user == null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dbCommon.DBTables#deleteTableWithAllData(
	 * )
	 */
	@Override
	public void deleteUser(User user) {
		try {
			sessionFactory.getCurrentSession().delete(user);
		} catch (Exception ex) {
			logger.error(ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.UsersDao#validate(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public boolean validate(String userLogin, String userPassword) {
		try {
			user = (User) sessionFactory.getCurrentSession().createCriteria(User.class)
					.add(Restrictions.eq("userLogin", userLogin)).add(Restrictions.eq("userPassword", userPassword))
					.uniqueResult();
		} catch (Exception ex) {
			logger.error(ex);
		}
		return user != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.UserDao#getAllCustomers()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllCustomers() {
		List<User> users = new ArrayList<User>();
		try {
			users = sessionFactory.getCurrentSession().createCriteria(User.class)
					.add(Restrictions.eq("userRole.userRoleName", "customer")).list();
		} catch (Exception ex) {
			logger.error(ex);
		}
		return users;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.UserDao#getAllWorkers()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllWorkers() {
		List<User> users = null;
		try {
			users = sessionFactory.getCurrentSession().createCriteria(User.class)
					.add(Restrictions.disjunction().add(Restrictions.eq("userRole.userRoleName", "worker"))
							.add(Restrictions.eq("userRole.userRoleName", "manager"))
							.add(Restrictions.eq("userRole.userRoleName", "storekeeper")))
					.list();
		} catch (Exception ex) {
			logger.error(ex);
		}
		return users;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.dao.UserDao#getUserByID(java.lang.Integer)
	 */
	@Override
	public User getUserByID(Integer userId) {
		User user = null;
		try {
			user = (User) sessionFactory.getCurrentSession().createCriteria(User.class)
					.add(Restrictions.eq("userId", userId.longValue())).uniqueResult();
		} catch (Exception ex) {
			logger.error(ex);
		}
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.dao.UserDao#getUserByID(java.lang.Integer)
	 */
	@Override
	public User getUserByLogin(String userLogin) {
		User user = null;
		try {
			user = (User) sessionFactory.getCurrentSession().createCriteria(User.class)
					.add(Restrictions.eq("userLogin", userLogin)).uniqueResult();
		} catch (Exception ex) {
			logger.error(ex);
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
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(user);
		} catch (Exception ex) {
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
	public void createNewUser(String userLogin, String userPassword, UserRole userRole) {
		User user = new User();
		user.setUserLogin(userLogin);
		user.setUserPassword(userPassword);
		user.setUserRole(userRole);
		try {
			sessionFactory.getCurrentSession().save(user);
		} catch (Exception ex) {
			logger.error(ex);
		}
	}

	public User getUser() {
		return user;
	}

}
