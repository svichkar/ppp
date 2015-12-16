/**
 * 
 */
package com.nixsolutions.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nixsolutions.dao.UserRoleDao;
import com.nixsolutions.entity.UserRole;
import com.nixsolutions.util.HibernateUtil;

/**
 * @author ������
 *
 */
public class UserRoleDaoImpl implements UserRoleDao {

	private final static Logger logger = LogManager.getLogger();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.UserRoleDao#getUserRole(java.
	 * lang.Integer)
	 */
	@Override
	public UserRole getUserRole(Integer user_role_id) {
		UserRole role = new UserRole();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			role = (UserRole) session.createCriteria(UserRole.class).add(Restrictions.eq("userRoleId", user_role_id))
					.uniqueResult();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return role;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.UserRoleDao#getUserRole(java.
	 * lang.Integer)
	 */
	@Override
	public UserRole getUserRole(String user_role_name) {
		UserRole role = new UserRole();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			role = (UserRole) session.createCriteria(UserRole.class).add(Restrictions.eq("userRoleName", user_role_name))
					.uniqueResult();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return role;
	}

	/*
	 * create new sqllab.role for existing customer
	 * 
	 * @see
	 * com.nixsolutions.serviceStation.dAOFabrica.CarDao#createNewCar(java.lang.
	 * String, java.lang.String, java.lang.String, java.lang.String)
	 */

	@Override
	public void createNewUserRole(UserRole user_role) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(user_role);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		}
	}

}
